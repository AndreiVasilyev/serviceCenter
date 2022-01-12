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
let messageElements = document.querySelectorAll('.alert-block-message');
let currentRoleElement = document.getElementById("current-role");
let currentRole = currentRoleElement.getAttribute('data-role');

//----- prepare validity checks for form fields

let orderNumberValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/[PS]\d{1,5}$/gm);
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
    if (currentRole === 'GUEST') {
        let isEmailCorrect = isFieldValid(emailInput);
        let isCodeCorrect = isFieldValid(codeInput);
        if (isOrderNumberCorrect && isEmailCorrect && sendCodeButton.hasAttribute('disabled')) {
            sendCodeButton.removeAttribute('disabled');
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

//Set listeners after page loaded

document.addEventListener('DOMContentLoaded', loadDocumentHandler);

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
    let url = '/control?command=send_code_command&email=' + emailInput.value;
    sendCodeButton.setAttribute('disabled', 'disabled');
    sendGetStringQuery(url).then(response => sendCodeResponseHandler(response));
}

//-----send email with confirmation code response handler

function sendCodeResponseHandler(response) {
    sendCodeButton.removeAttribute('disabled');
    if (response === 'error') {
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
    alertBlockElement.classList.remove('alert-.*');
    alertBlockElement.classList.add(messageClass);
    messageElements
        .forEach(element => element.classList.add('d-none'));
    messageElement.classList.remove('d-none');
}

//-----function deactivate code input element with info block

function deactivateCodeInputElement() {
    //noteMessageElement.classList.add('d-none');
    codeInput.setAttribute('disabled', 'disabled');
    codeInput.value = '';
    codeInput.dispatchEvent(new Event("blur"));
    alertBlockElement.classList.remove('d-flex');
    alertBlockElement.classList.add('d-none');
}

//-----submit form handler

function submitFormHandler() {
    orderNumberInput.value = '';
    orderNumberInput.classList.remove('is-valid');
    this.setAttribute('disabled', 'disabled');
    if (currentRole === 'GUEST') {
        sendCodeButton.setAttribute('disabled', 'disabled');
        deactivateCodeInputElement();
        emailInput.value = '';
        emailInput.classList.remove('is-valid');
    }

    //if code success - change role and hide email and show result search
    //else - show error message
}