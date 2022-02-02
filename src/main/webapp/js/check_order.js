//Check order form validation

//----- prepare variables for page elements

let orderNumberInput = document.getElementById('order-number-input');
let emailInput = document.getElementById('email-input');
let codeInput = document.getElementById('code-input');
let checkOrderButton = document.getElementById('check-order-button');
let sendCodeButton = document.getElementById('send-code-button');
let alertBlockElement = document.getElementById('alert-block');
let noteMessageElement = document.getElementById('note-input-code');
let iconElement = alertBlockElement.querySelector('use');
let errorMessageElement = document.getElementById('error-send-code');
let resultSearchMessageElement = document.getElementById('result-search-order');
let messageElements = document.querySelectorAll('.alert-block-message');
let currentRoleElement = document.getElementById("current-role");


//----- prepare validity checks for form fields

let orderNumberValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[PS]\d{1,5}$/gm);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let orderErrorElement = document.getElementById('order-number-error');
            return orderErrorElement.dataset.errorMatch;
        }
    }];
let emailAddressValidityChecks = [
    {
        isInvalid: function (inputField) {
            return inputField.value.length < 8;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('email-error');
            return emailErrorElement.dataset.errorSize;
        }
    },
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('email-error');
            return emailErrorElement.dataset.errorMatch;
        }
    }];
let verificationCodeValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/\b\d{5}\b/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let codeErrorElement = document.getElementById('code-error');
            return codeErrorElement.dataset.errorMatch;
        }
    }];

//----- prepare function to handle form when all fields validated

let validatedCheckOrderFormHandler = function () {
    let isOrderNumberCorrect = isFieldValid(orderNumberInput);
    let currentRole = currentRoleElement.dataset.role;
    if (currentRole === 'GUEST') {
        let isEmailCorrect = isFieldValid(emailInput);
        let isCodeCorrect = isFieldValid(codeInput);
        if (isOrderNumberCorrect && isEmailCorrect && sendCodeButton.hasAttribute('disabled')) {
            sendCodeButton.removeAttribute('disabled');
        }
        if ((!isOrderNumberCorrect || !isEmailCorrect) && !sendCodeButton.hasAttribute('disabled')) {
            sendCodeButton.setAttribute('disabled', 'disabled');
        }
        if (isOrderNumberCorrect && isEmailCorrect && isCodeCorrect) {
            document.getElementById('check-order-button').removeAttribute('disabled');
        }
    } else {
        if (isOrderNumberCorrect) {
            checkOrderButton.removeAttribute('disabled');
        }
    }
}

//----- creating custom validation for form fields

createCustomValidation(orderNumberInput, orderNumberValidityChecks);
createCustomValidation(emailInput, emailAddressValidityChecks);
createCustomValidation(codeInput, verificationCodeValidityChecks);

//Set listeners after page load/unload

document.addEventListener('DOMContentLoaded', loadDocumentHandler);
window.addEventListener('beforeunload', unloadDocumentHandler);

//-----page unload handler

function unloadDocumentHandler() {
    let event = localStorage.getItem('currentEvent');
    if (event !== 'changeLocale') {
        localStorage.clear();
    }
    localStorage.removeItem('currentEvent');
}

//-----page loaded handler

function loadDocumentHandler() {
    localeSelectorElement.addEventListener('change', changeLocaleHandler);
    sendCodeButton.addEventListener('click', sendCodeButtonHandler);
    checkOrderButton.addEventListener('click', submitFormHandler)
    let inputFieldElements = document.querySelectorAll('.form-control');
    for (let i = 0; i < inputFieldElements.length; i++) {
        inputFieldElements[i].onblur = onBlurInputFieldHandler;
        inputFieldElements[i].onfocus = onFocusInputFieldHandler;
        let lastValue = localStorage.getItem(inputFieldElements[i].name);
        if (lastValue != null) {
            inputFieldElements[i].value = lastValue;
        }
        if (inputFieldElements[i].value !== '' && !inputFieldElements[i].hasAttribute('disabled')) {
            checkInputField(inputFieldElements[i], validatedCheckOrderFormHandler, checkOrderButton);
        }
    }
    let isCodeActive = localStorage.getItem('isCodeActive');
    if (isCodeActive != null && isCodeActive === 'true') {
        codeInput.removeAttribute('disabled');
        checkInputField(codeInput, validatedCheckOrderFormHandler, checkOrderButton);
        activateCodeInputElement();
    }
    let isDisplayResults = localStorage.getItem('isDisplayResults');
    if (isDisplayResults != null && isDisplayResults === 'true') {
        let currentOrderNumber = localStorage.getItem('currentOrderNumber');
        let url = '/control';
        let searchParams = new URLSearchParams();
        searchParams.append('command', 'find_order_by_number');
        searchParams.append('orderNumber', currentOrderNumber);
        localStorage.removeItem('isDisplayResults');
        orderNumberInput.value = currentOrderNumber;
        sendPostFormQuery(url, searchParams).then(response => findOrderResponseHandler(response));
    }
}

//-----input field OnBlur event handler

function onBlurInputFieldHandler() {
    checkInputField(this, validatedCheckOrderFormHandler, checkOrderButton);
}

//-----input field OnFocus event handler

function onFocusInputFieldHandler() {
    checkOrderButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}

//-----send confirmation code button handler

function sendCodeButtonHandler() {
    let url = '/control?command=send_code&email=' + emailInput.value + '&orderNumber=' + orderNumberInput.value;
    sendCodeButton.setAttribute('disabled', 'disabled');
    let spinnerElement = document.createElement('span');
    spinnerElement.className = "spinner-border spinner-border-sm";
    sendCodeButton.prepend(spinnerElement);
    sendGetStringQuery(url).then(response => sendCodeResponseHandler(response));
}

//-----send email with confirmation code response handler

function sendCodeResponseHandler(response) {
    sendCodeButton.removeAttribute('disabled');
    sendCodeButton.firstChild.remove();
    let responseResult = response.split(":");
    if (responseResult[0] === 'error') {
        errorMessageElement.innerHTML = responseResult[1];
        activateAlertBlock(errorMessageElement, 'alert-danger', '#exclamation-triangle-fill');
    } else {
        localStorage.removeItem('isCodeActive');
        localStorage.setItem('isCodeActive', 'true');
        activateCodeInputElement();
    }
}

//-----function activate code input element with info block for 5 minutes

function activateCodeInputElement() {
    codeInput.removeAttribute('disabled');
    activateAlertBlock(noteMessageElement, 'alert-warning', '#exclamation-triangle-fill');
    setTimeout(() => {
        localStorage.removeItem('isCodeActive');
        localStorage.setItem('isCodeActive', 'false');
        deactivateCodeInputElement();
    }, 300000);
}

//-----function activate info block

function activateAlertBlock(messageElement, messageClass, iconId) {
    iconElement.removeAttribute('href');
    iconElement.setAttribute('href', iconId);
    alertBlockElement.classList.remove('d-none');
    alertBlockElement.classList.add('d-flex');
    alertBlockElement.classList.remove('alert-warning');
    alertBlockElement.classList.remove('alert-danger');
    alertBlockElement.classList.remove('alert-success');
    alertBlockElement.classList.add(messageClass);
    messageElements
        .forEach(element => element.classList.add('d-none'));
    messageElement.classList.remove('d-none');
}

//-----function deactivate info block

function deactivateAlertBlock() {
    alertBlockElement.classList.remove('d-flex');
    alertBlockElement.classList.add('d-none');
}

//-----function deactivate code input element with info block

function deactivateCodeInputElement() {
    //noteMessageElement.classList.add('d-none');
    codeInput.setAttribute('disabled', 'disabled');
    codeInput.value = '';
    codeInput.dispatchEvent(new Event("blur"));
    deactivateAlertBlock();
}

//-----function deactivate result block

function deactivateResultsBlock() {
    let bodyPartsElement = document.querySelector('.body-parts');
    bodyPartsElement.innerHTML = '';
    let bodyOrderElements = document.querySelectorAll('.td-order');
    Array.from(bodyOrderElements).forEach((cell) => cell.innerHTML = '');
    let resultsElement = document.querySelector('.results');
    resultsElement.classList.add('d-none');
}


//-----submit form handler

function submitFormHandler() {
    let url = '/control';
    let searchParams = new URLSearchParams();
    deactivateAlertBlock();
    deactivateResultsBlock();
    searchParams.append('command', 'find_order_by_number');
    searchParams.append('orderNumber', orderNumberInput.value);
    this.setAttribute('disabled', 'disabled');
    let currentRole = currentRoleElement.dataset.role
    if (currentRole === 'GUEST') {
        searchParams.append('email', emailInput.value);
        searchParams.append('code', codeInput.value);
        sendCodeButton.setAttribute('disabled', 'disabled');
    }
    sendPostFormQuery(url, searchParams).then(response => findOrderResponseHandler(response));
}

//-----find order results handler

function findOrderResponseHandler(response) {
    let currentRole = currentRoleElement.dataset.role;
    if (typeof response === 'object') {
        if (currentRole === 'GUEST') {
            currentRoleElement.dataset.role = 'CLIENT';
            currentRoleElement.innerHTML = 'CLIENT';
            emailInput.setAttribute('disabled', 'disabled');
            codeInput.setAttribute('disabled', 'disabled');
        }
        activateResultsHeader(currentRole);
        showResults(response);
    } else {
        let splitedResponse = response.split(':');
        let responseResult = splitedResponse[0];
        let responseText = splitedResponse[1];
        if (responseResult === 'ok') {
            activateResultsHeader(currentRole);
            let div = document.createElement('div');
            div.innerHTML = responseText;
            resultSearchMessageElement.append(div);
        } else {
            errorMessageElement.innerHTML = responseText;
            activateAlertBlock(errorMessageElement, 'alert-danger', '#exclamation-triangle-fill');
            localStorage.setItem('currentOrderNumber', orderNumberInput.value);
        }
    }
}

//-----function activate message block for results

function activateResultsHeader(currentRole) {
    localStorage.clear();
    resetInputFields(currentRole);
    activateAlertBlock(resultSearchMessageElement, 'alert-success', '#check-circle-fill');
    let lastElement = resultSearchMessageElement.lastElementChild;
    if (lastElement != null) {
        lastElement.remove();
    }
}

//-----function clear all active input fields

function resetInputFields(currentRole) {
    localStorage.setItem('currentOrderNumber', orderNumberInput.value);
    orderNumberInput.value = '';
    orderNumberInput.classList.remove('is-valid');
    if (currentRole === 'GUEST') {
        deactivateCodeInputElement();
        emailInput.value = '';
        emailInput.classList.remove('is-valid');
    }
}

//-----function fill results table and show it

function showResults(response) {
    let resultsElement = document.querySelector('.results');
    resultsElement.classList.remove('d-none');
    fillCell('.td-number', response.orderNumber);
    let orderStatus = response.orderStatus;
    let orderStatusElement = document.querySelector('.td-status');
    orderStatusElement.innerHTML = orderStatus;
    orderStatusElement.classList.remove('table-danger', 'table-success', 'table-warning', 'table-info');
    switch (orderStatus) {
        case 'ACCEPTED':
            orderStatusElement.classList.add('table-danger');
            break;
        case 'IN_PROGRESS':
            orderStatusElement.classList.add('table-warning');
            break;
        case 'CLOSED':
            orderStatusElement.classList.add('table-info');
            break;
        case 'ISSUED':
            orderStatusElement.classList.add('table-success');
            break;
    }
    let creationDate = response.creationDate.replace(/\d\d:\d\d:\d\d/, '');
    fillCell('.td-creation', creationDate);
    fillCell('.td-device', response.device.name);
    fillCell('.td-company', response.company.name);
    fillCell('.td-model', response.model);
    fillCell('.td-serial', response.serialNumber);
    let completionDate = response.completionDate;
    if (completionDate != null && completionDate !== '') {
        completionDate = completionDate.replace(/\d\d:\d\d:\d\d/, '');
    } else {
        completionDate = '';
    }
    fillCell('.td-completion', completionDate);
    let issueDate = response.issueDate;
    if (issueDate != null && issueDate !== '') {
        issueDate = issueDate.replace(/\d\d:\d\d:\d\d/, '');
    } else {
        issueDate = '';
    }
    fillCell('.td-issue', issueDate);
    fillCell('.td-work', response.workDescription != null ? response.workDescription : '');
    let workPrice = response.workPrice;
    let repairCost = '';
    if (workPrice != null) {
        repairCost = workPrice.repairCost;
    }
    fillCell('.td-work-cost', repairCost);
    if (response.spareParts != null && response.spareParts.length > 0) {
        response.spareParts.forEach(createPartRow);
    }
    let partsCost = Array.from(document.querySelectorAll('.td-part-cost'))
        .map(item => Number.parseInt(item.innerHTML))
        .reduce((sum, current) => sum + current, 0);
    fillCell('.td-total-cost', repairCost + partsCost);
}

//-----function create spare part row element

function createPartRow(item) {
    let bodyPartsElement = document.querySelector('.body-parts');
    let rowElement = document.createElement('tr');
    addPartCell(rowElement, 'td-part-number', item.partNumber);
    addPartCell(rowElement, 'td-part-name', item.name);
    addPartCell(rowElement, 'td-description', item.description);
    addPartCell(rowElement, 'td-part-cost', item.cost);
    bodyPartsElement.append(rowElement);
}

//-----function create and add spare part cell element

function addPartCell(rowElement, className, value) {
    let td = document.createElement('td');
    td.setAttribute('class', className);
    td.innerHTML = value;
    rowElement.append(td);
}

//-----function insert value in table cell

function fillCell(cellSelector, value) {
    let cell = document.querySelector(cellSelector);
    cell.innerHTML = value;
}

