//Check order form validation

//----- prepare variables for page elements

let loginInput = document.getElementById('login-input');
let passwordInput = document.getElementById('password-input');
let loginButton = document.getElementById('login-button');
let rememberInput = document.getElementById('remember-input');
let alertBlockElement = document.getElementById('alert-block');
let noteMessageElement = document.getElementById('note-input-code');
let iconElement = alertBlockElement.querySelector('use');
let errorMessageElement = document.getElementById('error-send-code');
let messageElements = document.querySelectorAll('.alert-block-message');
let currentRoleElement = document.getElementById("current-role");


//----- prepare validity checks for form fields

let loginValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\wа-яА-Я@.]{3,20}$/gm);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('login-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];
let passwordValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\w^_]{8,20}$/gm);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let passwordErrorElement = document.getElementById('password-error');
            return passwordErrorElement.dataset.errorMatch;
        }
    }];

//----- prepare function to handle form when all fields validated

let validatedLoginFormHandler = function () {
    let isLoginCorrect = isFieldValid(loginInput);
    let isPasswordCorrect = isFieldValid(passwordInput);
    if (isLoginCorrect && isPasswordCorrect) {
        loginButton.removeAttribute('disabled');
    }
}

//----- creating custom validation for form fields

createCustomValidation(loginInput, loginValidityChecks);
createCustomValidation(passwordInput, passwordValidityChecks);

//Set listeners after page load/unload

document.addEventListener('DOMContentLoaded', loadDocumentHandler);
window.addEventListener('beforeunload', unloadDocumentHandler);

//-----page unload handler

function unloadDocumentHandler() {
    let event = localStorage.getItem('currentEvent');
    if (event !== 'changeLocale') {
        console.log('clear storage');
        localStorage.clear();
    }
    localStorage.removeItem('currentEvent');
}

//-----page loaded handler

function loadDocumentHandler() {
    localeSelectorElement.addEventListener('change', changeLocaleHandler);
    let inputFieldElements = document.querySelectorAll('.form-control');
    for (let i = 0; i < inputFieldElements.length; i++) {
        inputFieldElements[i].onblur = onBlurInputFieldHandler;
        inputFieldElements[i].onfocus = onFocusInputFieldHandler;
        let lastValue = localStorage.getItem(inputFieldElements[i].name);
        if (lastValue != null) {
            inputFieldElements[i].value = lastValue;
        }
        if (inputFieldElements[i].value !== '') {
            checkInputField(inputFieldElements[i], validatedLoginFormHandler, loginButton);
        }
    }
}

//-----input field OnBlur event handler

function onBlurInputFieldHandler() {
    checkInputField(this, validatedLoginFormHandler, loginButton);
}

//-----input field OnFocus event handler

function onFocusInputFieldHandler() {
    loginButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}