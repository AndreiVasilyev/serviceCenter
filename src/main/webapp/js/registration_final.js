//Check registration form validation

//----- prepare variables for page elements

let registrationLoginInput = document.getElementById('registration-login-input');
let registrationPasswordInput = document.getElementById('registration-password-input');
let registrationPasswordConfirmInput = document.getElementById('registration-password-confirm-input');
let registrationFirstNameInput = document.getElementById('registration-first-name-input');
let registrationSecondNameInput = document.getElementById('registration-second-name-input');
let registrationPatronymicInput = document.getElementById('registration-patronymic-input');
let registrationEmailInput = document.getElementById('registration-email-input');
let registrationPostcodeInput = document.getElementById('registration-postcode-input');
let registrationCountryInput = document.getElementById('registration-country-input');
let registrationStateInput = document.getElementById('registration-state-input');
let registrationRegionInput = document.getElementById('registration-region-input');
let registrationCityInput = document.getElementById('registration-city-input');
let registrationStreetInput = document.getElementById('registration-street-input');
let registrationHouseInput = document.getElementById('registration-house-number-input');
let registrationApartmentInput = document.getElementById('registration-apartment-number-input');
let registrationPhone1Input = document.getElementById('registration-phone-1-input');
let registrationPhone2Input = document.getElementById('registration-phone-2-input');
let registrationPhone3Input = document.getElementById('registration-phone-3-input');
let registrationSaveButton = document.getElementById('registration-save-button');
let registrationInputElements = document.querySelectorAll('.registration-final');


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


let registrationPasswordValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\w^_]{8,20}$/gm);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let passwordErrorElement = document.getElementById('registration-password-error');
            return passwordErrorElement.dataset.errorMatch;
        }
    }];

let registrationPasswordConfirmValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\w^_]{8,20}$/gm);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let passwordErrorElement = document.getElementById('registration-password-confirm-error');
            return passwordErrorElement.dataset.errorMatch;
        }
    }, {
        isInvalid: function (inputField) {
            let passwordInputValue = registrationPasswordInput.value;
            let isPasswordMatches = inputField.value == passwordInputValue;
            return !isPasswordMatches;
        },
        invalidityMessage: function () {
            let passwordErrorElement = document.getElementById('registration-password-confirm-error');
            return passwordErrorElement.dataset.errorEquals;
        }
    }];

let registrationFirstNameValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-ZА-Яа-я-]{3,20}$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('registration-first-name-error');
            return emailErrorElement.dataset.errorMatch;
        }
    }];

let registrationSecondNameValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-ZА-Яа-я-]{3,40}$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('registration-second-name-error');
            return emailErrorElement.dataset.errorMatch;
        }
    }];

let registrationPatronymicValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-ZА-Яа-я]{3,20})?$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('registration-patronymic-error');
            return emailErrorElement.dataset.errorMatch;
        }
    }];

let registrationEmailAddressValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([^\s@]+@[^\s@]+\.[^\s@]+)?$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('registration-email-error');
            return emailErrorElement.dataset.errorMatch;
        }
    }];

let registrationCountryValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я- ]{3,30})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-country-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationPostcodeValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^(\d{6})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-postcode-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationStateValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я\. ]{8,40})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-state-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationRegionValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я\. -]{8,40})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-region-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationCityValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-Zа-яА-Я- ]{3,30}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-city-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationStreetValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-Zа-яА-Я- \.]{5,40}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-street-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];
let registrationHouseValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\d]{1,4}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-house-number-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationApartmentValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\d]{1,4})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-apartment-number-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationPhoneValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^(\+\d{12})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-phones-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registrationPhoneRequiredValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^\+\d{12}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('registration-phones-error');
            return loginErrorElement.dataset.errorRequired;
        }
    }];


//----- prepare function to handle form when all fields validated

let validatedRegistrationHandler = function () {
    let isFormValid = true;
    Array.from(registrationInputElements).forEach(element => {
        if (element.classList.contains('required')) {
            if (element.value == '' || !isFieldValid(element)) {
                registrationSaveButton.setAttribute('disabled', 'disabled');
                isFormValid = false;
            }
        } else {
            if (isFieldInvalid(element)) {
                registrationSaveButton.setAttribute('disabled', 'disabled');
                isFormValid = false;
            }
        }
    });
    if (isFormValid) {
        registrationSaveButton.removeAttribute('disabled');
    }
}

//----- creating custom validation for form fields

createCustomValidation(registrationLoginInput, registrationLoginValidityChecks);
createCustomValidation(registrationPasswordInput, registrationPasswordValidityChecks);
createCustomValidation(registrationPasswordConfirmInput, registrationPasswordConfirmValidityChecks);
createCustomValidation(registrationFirstNameInput, registrationFirstNameValidityChecks);
createCustomValidation(registrationSecondNameInput, registrationSecondNameValidityChecks);
createCustomValidation(registrationPatronymicInput, registrationPatronymicValidityChecks);
createCustomValidation(registrationPostcodeInput, registrationPostcodeValidityChecks);
createCustomValidation(registrationCountryInput, registrationCountryValidityChecks);
createCustomValidation(registrationStateInput, registrationStateValidityChecks);
createCustomValidation(registrationEmailInput, registrationEmailAddressValidityChecks);
createCustomValidation(registrationRegionInput, registrationRegionValidityChecks);
createCustomValidation(registrationCityInput, registrationCityValidityChecks);
createCustomValidation(registrationStreetInput, registrationStreetValidityChecks);
createCustomValidation(registrationHouseInput, registrationHouseValidityChecks);
createCustomValidation(registrationApartmentInput, registrationApartmentValidityChecks);
createCustomValidation(registrationPhone1Input, registrationPhoneRequiredValidityChecks);
createCustomValidation(registrationPhone2Input, registrationPhoneValidityChecks);
createCustomValidation(registrationPhone3Input, registrationPhoneValidityChecks);

//Set listeners after page load/unload

document.addEventListener('DOMContentLoaded', loadRegistrationHandler);
window.addEventListener('beforeunload', unloadRegistrationHandler);


function loadRegistrationHandler() {
    localeSelectorElement.addEventListener('change', changeLocaleHandler);
    for (let i = 0; i < registrationInputElements.length; i++) {
        registrationInputElements[i].onblur = onBlurRegistrationInputFieldHandler;
        registrationInputElements[i].onfocus = onFocusRegistrationInputFieldHandler;
        let lastValue = localStorage.getItem(registrationInputElements[i].name);
        if (lastValue != null) {
            registrationInputElements[i].value = lastValue;
        }
        if (registrationInputElements[i].value !== '') {
            checkInputField(registrationInputElements[i], validatedRegistrationHandler, registrationSaveButton);
        }
    }
}

function unloadRegistrationHandler() {

}

function onBlurRegistrationInputFieldHandler(event) {
    let currentElement = event.currentTarget;
    checkInputField(this, validatedRegistrationHandler, registrationSaveButton);
    if (currentElement.id == 'registration-phone-1-input') {
        if (currentElement.classList.contains('is-valid')) {
            registrationPhone2Input.removeAttribute('readonly');
            registrationPhone3Input.removeAttribute('readonly');
        } else {
            registrationPhone2Input.setAttribute('readonly', 'readonly');
            registrationPhone3Input.setAttribute('readonly', 'readonly');
            registrationPhone2Input.classList.remove('is-valid', 'is-invalid');
            registrationPhone3Input.classList.remove('is-valid', 'is-invalid');
            registrationPhone2Input.value = '';
            registrationPhone3Input.value = '';
        }
    }
}

function onFocusRegistrationInputFieldHandler() {
    registrationSaveButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}