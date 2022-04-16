let companiesFilterInputElements = document.querySelectorAll('.company-filter-input');
let companiesSortLinkElements = document.querySelectorAll('.company-sort-link');
let clearCompaniesFilterButton = document.querySelector('.clear-companies-filter');
let companyNameInputElement = document.getElementById('company-name-input');
let companyContractInputElement = document.getElementById('company-contract-input');
let addCompanyButton = document.getElementById('add-company-button');
let newCompanyResultBlock = document.getElementById('alert-new-company-result');
let newCompanyResultMessageElement = document.getElementById('new-company-result-message');
let isContractFilterInputElement = document.getElementById('is-contract-filter-input');

let companyNameValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-zа-яА-Я]{1,20}( [a-zA-zа-яА-Я]{1,20}){0,3}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('company-name-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

createCustomValidation(companyNameInputElement, companyNameValidityChecks);

let validatedNewCompanyFormHandler = function () {
    if (isFieldValid(companyNameInputElement)) {
        addCompanyButton.removeAttribute('disabled');
    }
}

function companiesTabLoadHandler() {
    Array.from(companiesSortLinkElements).forEach(element => element.addEventListener('click', changeSortCompaniesHandler));
    Array.from(companiesFilterInputElements).forEach(element => element.addEventListener('input', changeCompaniesFilterInputHandler));
    clearCompaniesFilterButton.addEventListener('click', clearCompaniesFilterButtonHandler);
    companyNameInputElement.classList.remove('is-valid', 'is-invalid');
    companyNameInputElement.value = '';
    companyNameInputElement.addEventListener('blur', onBlurNewCompanyInputHandler);
    companyNameInputElement.addEventListener('focus', onFocusNewCompanyInputHandler);
    companyContractInputElement.addEventListener('change', onChangeIsContractHandler)
    isContractFilterInputElement.indeterminate = true;
    isContractFilterInputElement.addEventListener('click', onClickCheckboxFilterHandler);
    newCompanyResultBlock.classList.replace('d-flex', 'd-none');
    addCompanyButton.addEventListener('click', addCompanyButtonClickHandler);
    getCompaniesDataFromServer();
}

function onChangeIsContractHandler() {
    this.value = this.checked ? '1' : '0';
}

function onClickCheckboxFilterHandler() {
    if (this.value == '') {
        this.value = '1';
    } else if (this.value == '1') {

        this.value = '0';
    } else {
        this.indeterminate = true;
        this.value = '';
        if (this.checked) {
            this.checked = false;
        }
    }
}

function onBlurNewCompanyInputHandler() {
    checkInputField(this, validatedNewCompanyFormHandler, addCompanyButton);
}

function onFocusNewCompanyInputHandler() {
    newCompanyResultBlock.classList.replace('d-flex', 'd-none');
    addCompanyButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}

function addCompanyButtonClickHandler() {
    let parameters = {};
    parameters['name'] = companyNameInputElement.value;
    parameters['isContract'] = companyContractInputElement.value;
    let controller = '/control?command=add_new_company';
    sendPostJsonQuery(controller, parameters).then(response => addCompanyResponseHandler(response));
}

function addCompanyResponseHandler(response) {
    newCompanyResultBlock.classList.replace('d-none', 'd-flex');
    let splitResult = response.split(':');
    newCompanyResultMessageElement.innerHTML = splitResult[1];
    if (splitResult[0] === 'ok') {
        companyNameInputElement.value = '';
        companyNameInputElement.classList.remove('is-valid', 'is-invalid');
        companyContractInputElement.value = '';
        addCompanyButton.setAttribute('disabled', 'disabled');
        getCompaniesDataFromServer();
    } else {
        companyNameInputElement.classList.add('is-invalid');
    }
}

function getCompaniesDataFromServer() {
    companiesFilterInputAccess(false);
    let parameters = collectCurrentCompaniesParameters(companiesFilterInputElements);
    let controller = '/control?command=find_companies';
    console.log('before send query:' + parameters['id'] + ',' + parameters['name'] + ',' + parameters['isContract']);
    sendPostJsonQuery(controller, parameters).then(response => findCompaniesResponseHandler(response));
}

function companiesFilterInputAccess(accessibility) {
    if (accessibility) {
        Array.from(companiesFilterInputElements).forEach(element => element.removeAttribute('disabled'));
    } else {
        Array.from(companiesFilterInputElements).forEach(element => element.setAttribute('disabled', 'disabled'));
    }
}

function collectCurrentCompaniesParameters(elements) {
    let parameters = {};
    parameters['id'] = elements[0].value;
    parameters['name'] = elements[1].value;
    parameters['isContract'] = elements[2].value;
    let currentSortElements = Array.from(companiesSortLinkElements)
        .map(element => element.firstElementChild)
        .filter(element => element.dataset.sort !== '');
    if (currentSortElements != null && currentSortElements.length > 0) {
        parameters['sortByName'] = currentSortElements[0].dataset.column;
        parameters['sortDirection'] = currentSortElements[0].dataset.sort;
    } else {
        parameters['sortByName'] = '';
        parameters['sortDirection'] = '';
    }
    return parameters;
}

function findCompaniesResponseHandler(response) {
    console.log('response:' + response);
    let oldRowElements = document.querySelectorAll('.company-row');
    Array.from(oldRowElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let tableBodyElement = document.querySelector('.companies-body');
        for (const company of response) {
            let rowElement = document.createElement('tr');
            rowElement.classList.add('company-row');
            let id = company.id !== 0 ? company.id : '';
            let name = company.name != null ? company.name : '';
            let isContract = company.name;
            rowElement = appendTableCell(id, rowElement);
            rowElement = appendTableCell(name, rowElement);
            rowElement = appendTableCell(isContract, rowElement);
            rowElement = appendTableCell('', rowElement);
            tableBodyElement.append(rowElement);
        }
    }
    companiesFilterInputAccess(true);
}

function changeSortCompaniesHandler(event) {
    let currentElement = event.currentTarget.firstElementChild;
    let currentSortState = currentElement.dataset.sort;
    Array.from(companiesSortLinkElements).map(element => element.firstElementChild)
        .filter(element => element.classList.contains('fa-sort-asc') || element.classList.contains('fa-sort-desc'))
        .forEach(element => {
            element.classList.remove('fa-sort-asc', 'fa-sort-desc');
            element.dataset.sort = '';
        })
    if (currentSortState === 'asc') {
        currentElement.classList.add('fa-sort-desc');
        currentElement.dataset.sort = 'desc';
    } else if (currentSortState === 'desc') {
        currentElement.classList.add('fa-sort');
        currentElement.dataset.sort = '';
    } else {
        currentElement.classList.add('fa-sort-asc');
        currentElement.dataset.sort = 'asc';
    }
    getCompaniesDataFromServer();
}

function changeCompaniesFilterInputHandler() {
    clearTimeout(typingTimer);
    typingTimer = setTimeout(getCompaniesDataFromServer, typingTimerInterval);
}

function clearCompaniesFilterButtonHandler() {
    Array.from(companiesFilterInputElements).forEach(element => element.value = '');
    isContractFilterInputElement.indeterminate = true;
    if (isContractFilterInputElement.checked) {
        isContractFilterInputElement.checked = false;
    }
    getCompaniesDataFromServer();
}