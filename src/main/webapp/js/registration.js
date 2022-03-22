//Check registration form validation

//----- prepare variables for page elements

let registrationLoginInput = document.getElementById('registration-login-input');
let registrationRoleInput = document.getElementById('registration-employee-role');
let nextRegistrationButton = document.getElementById('registration-next-button');
let firstStepInputFieldElements = document.querySelectorAll('.registration-first-step');


//----- prepare validity checks for form fields

let registrationLoginValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\wа-яА-Я@.]{3,20}$/gm);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-login-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationRoleValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\w]{1,50}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-employee-role-error');
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

let validatedRegistrationFirstStepHandler = function () {
    let isLoginCorrect = isFieldValid(registrationLoginInput);
    let isRoleCorrect = isFieldValid(registrationRoleInput);
    if (isLoginCorrect && isRoleCorrect) {
        nextRegistrationButton.removeAttribute('disabled');
    }
}

//----- creating custom validation for form fields

createCustomValidation(registrationLoginInput, registrationLoginValidityChecks);
createCustomValidation(registrationRoleInput, registrationRoleValidityChecks);

//Set listeners after page load/unload

document.addEventListener('DOMContentLoaded', loadRegistrationFirstStepHandler);
window.addEventListener('beforeunload', unloadRegistrationFirstStepHandler);

//-----page unload handler

function unloadRegistrationFirstStepHandler() {
    let event = localStorage.getItem('currentEvent');
    if (event !== 'changeLocale') {
        console.log('clear storage');
        localStorage.clear();
    }
    localStorage.removeItem('currentEvent');
}

//-----page loaded handler

function loadRegistrationFirstStepHandler() {
    localeSelectorElement.addEventListener('change', changeLocaleHandler);
    for (let i = 0; i < firstStepInputFieldElements.length; i++) {
        firstStepInputFieldElements[i].onblur = onBlurRegistrationFirstStepInputFieldHandler;
        firstStepInputFieldElements[i].onfocus = onFocusRegistrationFirstStepnputFieldHandler;
        let lastValue = localStorage.getItem(firstStepInputFieldElements[i].name);
        if (lastValue != null) {
            firstStepInputFieldElements[i].value = lastValue;
        }
        if (firstStepInputFieldElements[i].value !== '') {
            checkInputField(firstStepInputFieldElements[i], validatedRegistrationFirstStepHandler, nextRegistrationButton);
        }
    }
}

function onBlurRegistrationFirstStepInputFieldHandler() {
    checkInputField(this, validatedRegistrationFirstStepHandler, nextRegistrationButton);
}

function onFocusRegistrationFirstStepnputFieldHandler() {
    nextRegistrationButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}