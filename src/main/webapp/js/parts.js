let partsFilterInputElements = document.querySelectorAll('.part-filter-input');
let newPartInputElements = document.querySelectorAll('.add-part');
let partsSortLinkElements = document.querySelectorAll('.part-sort-link');
let clearPartsFilterButton = document.querySelector('.clear-parts-filter');
let partNumberInputElement = document.getElementById('part-number-input');
let partNameInputElement = document.getElementById('part-name-input');
let partDescriptionInputElement = document.getElementById('part-description-input');
let partCostInputElement = document.getElementById('part-cost-input');
let addPartButton = document.querySelector('.save-new-part');
let newPartResultBlock = document.getElementById('alert-new-part-result');
let newPartResultMessageElement = document.getElementById('new-part-result-message');

let partNumberValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я \-]{3,30})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('part-number-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let partNameValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\wа-яА-Я ]{2,40}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('part-name-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let partDescriptionValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я].{1,100})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('part-description-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let partCostValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^\d+(\.\d{2})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('part-cost-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

createCustomValidation(partNumberInputElement, partNumberValidityChecks);
createCustomValidation(partNameInputElement, partNameValidityChecks);
createCustomValidation(partDescriptionInputElement, partDescriptionValidityChecks);
createCustomValidation(partCostInputElement, partCostValidityChecks);

let validatedNewPartFormHandler = function () {
    let isPartNumberCorrect = isFieldValid(partNumberInputElement) || partNumberInputElement.value == '';
    let isPartNameCorrect = isFieldValid(partNameInputElement);
    let isPartDescriptionCorrect = isFieldValid(partDescriptionInputElement) || partDescriptionInputElement.value == '';
    let isPartCostCorrect = isFieldValid(partCostInputElement);
    if (isPartNumberCorrect && isPartNameCorrect && isPartDescriptionCorrect && isPartCostCorrect) {
        addPartButton.removeAttribute('disabled');
    }
}

function partsTabLoadHandler() {
    Array.from(partsSortLinkElements).forEach(element => element.addEventListener('click', changeSortPartsHandler));
    Array.from(partsFilterInputElements).forEach(element => element.addEventListener('input', changePartsFilterInputHandler));
    clearPartsFilterButton.addEventListener('click', clearPartsFilterButtonHandler);
    Array.from(newPartInputElements).forEach(element => {
        element.classList.remove('is-valid', 'is-invalid');
        element.value = '';
        element.addEventListener('blur', onBlurNewPartInputHandler);
        element.addEventListener('focus', onFocusNewPartInputHandler);
    })
    newPartResultBlock.classList.replace('d-flex', 'd-none');
    addPartButton.addEventListener('click', addPartButtonClickHandler);
    getPartsDataFromServer();
}

function onBlurNewPartInputHandler() {
    checkInputField(this, validatedNewPartFormHandler, addPartButton);
}

function onFocusNewPartInputHandler(event) {
    newPartResultBlock.classList.replace('d-flex', 'd-none');
    addPartButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}

function addPartButtonClickHandler() {
    let parameters = {};
    parameters['partNumber'] = partNumberInputElement.value;
    parameters['name'] = partNameInputElement.value;
    parameters['description'] = partDescriptionInputElement.value;
    parameters['cost'] = partCostInputElement.value;
    let controller = '/control?command=add_new_part';
    sendPostJsonQuery(controller, parameters).then(response => addPartResponseHandler(response));
}

function addPartResponseHandler(response) {
    newPartResultBlock.classList.replace('d-none', 'd-flex');
    let splitResult = response.split(':');
    newPartResultMessageElement.innerHTML = splitResult[1];
    if (splitResult[0] === 'ok') {
        Array.from(newPartInputElements).forEach(element => {
            element.value = '';
            element.classList.remove('is-valid', 'is-invalid');
        });
        addPartButton.setAttribute('disabled', 'disabled');
        getPartsDataFromServer();
    } else {
        Array.from(newPartInputElements).forEach(element => element.classList.add('is-invalid'));
    }
}

function getPartsDataFromServer() {
    partsFilterInputAccess(false);
    let parameters = collectCurrentPartsParameters(partsFilterInputElements);
    let controller = '/control?command=find_parts';
    sendPostJsonQuery(controller, parameters).then(response => findPartsResponseHandler(response));
}

function partsFilterInputAccess(accessibility) {
    Array.from(partsFilterInputElements).forEach(element => {
        if (accessibility) {
            element.removeAttribute('disabled');
        } else {
            element.setAttribute('disabled', 'disabled');
        }
    });
}

function collectCurrentPartsParameters(elements) {
    let parameters = {};
    parameters['id'] = elements[0].value;
    parameters['partNumber'] = elements[1].value;
    parameters['name'] = elements[2].value;
    parameters['description'] = elements[3].value;
    parameters['cost'] = elements[4].value;
    let currentSortElements = Array.from(partsSortLinkElements)
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

function findPartsResponseHandler(response) {
    console.log('DONE, response:' + response);
    let oldRowElements = document.querySelectorAll('.part-row');
    Array.from(oldRowElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let tableBodyElement = document.querySelector('.parts-body');
        for (const part of response) {
            let rowElement = document.createElement('tr');
            rowElement.classList.add('part-row');
            let id = part.id !== 0 ? part.id : '';
            let partNumber = part.partNumber != null ? part.partNumber : '';
            let name = part.name != null ? part.name : '';
            let description = part.description != null ? part.description : '';
            let cost = part.cost != null ? part.cost : '';
            rowElement = appendTableCell(id, rowElement);
            rowElement = appendTableCell(partNumber, rowElement);
            rowElement = appendTableCell(name, rowElement);
            rowElement = appendTableCell(description, rowElement);
            rowElement = appendTableCell(cost, rowElement);
            tableBodyElement.append(rowElement);
        }
    }
    partsFilterInputAccess(true);
}

function changeSortPartsHandler(event) {
    let currentElement = event.currentTarget.firstElementChild;
    let currentSortState = currentElement.dataset.sort;
    Array.from(partsSortLinkElements).map(element => element.firstElementChild)
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
    getPartsDataFromServer();
}

function changePartsFilterInputHandler() {
    clearTimeout(typingTimer);
    typingTimer = setTimeout(getPartsDataFromServer, typingTimerInterval);
}

function clearPartsFilterButtonHandler() {
    Array.from(partsFilterInputElements).forEach(element => element.value = '');
    getPartsDataFromServer();
}