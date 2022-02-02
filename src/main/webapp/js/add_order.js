//Check order form validation

//----- prepare variables for page elements

let orderTypeInput = document.getElementById('order-type');
let deviceInput = document.getElementById('order-device');
let companyInput = document.getElementById('order-company');
let modelInput = document.getElementById('order-model');
let serialInput = document.getElementById('order-serial');
let findPhoneInput = document.getElementById('find-phone');
let emailInput = document.getElementById('order-email');
let firstNameInput = document.getElementById('order-first-name');
let secondNameInput = document.getElementById('order-second-name');
let patronymicInput = document.getElementById('order-patronymic');
let phone1Input = document.getElementById('order-phone-1');
let phone2Input = document.getElementById('order-phone-2');
let phone3Input = document.getElementById('order-phone-3');
let saveOrderButton = document.getElementById('save-button');
let findPhoneButton = document.getElementById('find-phone-button');
//----- prepare validity checks for form fields

let orderTypeValidityChecks = [
    {
        isInvalid: function (inputField) {
            return inputField.value == 'NO';
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('order-type-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let deviceValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-zа-яА-Я]{3,20})( [a-zA-zа-яА-Я]{1,20}){0,4}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('order-device-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let companyValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-zа-яА-Я]{3,20})( [a-zA-zа-яА-Я]{1,20}){0,2}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('order-company-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let modelValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\wа-яА-Я -]{3,20}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('order-model-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let serialValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\wа-яА-Я -]{3,20}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('order-serial-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let phoneFindValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^\+\d{3}-\d{2}-\d{7}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('find-phone-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let emailAddressValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([^\s@]+@[^\s@]+\.[^\s@]+)?$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('order-email-error');
            return emailErrorElement.dataset.errorMatch;
        }
    }];

let firstNameValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-ZА-Яа-я-]{3,20}$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('client-names-error');
            return emailErrorElement.dataset.errorFirst;
        }
    }];

let secondNameValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-ZА-Яа-я-]{3,40}$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('client-names-error');
            return emailErrorElement.dataset.errorSecond;
        }
    }];

let patronymicValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-ZА-Яа-я]{3,20}$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('client-names-error');
            return emailErrorElement.dataset.errorPatronymic;
        }
    }];

let phoneValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^(\+\d{3}-\d{2}-\d{7})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-phones-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

//----- prepare function to handle form when all fields validated

let validatedNewOrderFormHandler = function () {
    let inputElements = document.querySelectorAll('.form-control,.form-select');
    let inputChecks = {};
    Array.from(inputElements).forEach(element => inputChecks[element.getAttribute('name')] = isFieldValid(element));
    /*let isOrderTypeCorrect = isFieldValid(orderTypeInput);
    let isDeviceCorrect = isFieldValid(deviceInput);
    let isCompanyCorrect = isFieldValid(companyInput);
    let isModelCorrect = isFieldValid(modelInput);
    let isSerialCorrect = isFieldValid(serialInput);
    let isFindPhoneCorrect = isFieldValid(findPhoneInput);
    if (isFindPhoneCorrect) {
        findPhoneButton.removeAttribute('disabled');
    }
    if (isOrderTypeCorrect && isDeviceCorrect && isCompanyCorrect && isModelCorrect && isSerialCorrect) {
        saveOrderButton.removeAttribute('disabled');
    }*/
    if (inputChecks['findPhone']) {
        findPhoneButton.removeAttribute('disabled');
    }
    for (key in inputChecks) {
        if (inputChecks[key] == false && key != 'findPhone') {
            return;
        }
    }
    saveOrderButton.removeAttribute('disabled');
}
//----- creating custom validation for form fields

createCustomValidation(orderTypeInput, orderTypeValidityChecks);
createCustomValidation(deviceInput, deviceValidityChecks);
createCustomValidation(companyInput, companyValidityChecks);
createCustomValidation(modelInput, modelValidityChecks);
createCustomValidation(serialInput, serialValidityChecks);
createCustomValidation(findPhoneInput, phoneFindValidityChecks);
createCustomValidation(emailInput, emailAddressValidityChecks);
createCustomValidation(firstNameInput, firstNameValidityChecks);
createCustomValidation(secondNameInput, secondNameValidityChecks);
createCustomValidation(patronymicInput, patronymicValidityChecks);
createCustomValidation(phone1Input, phoneValidityChecks);
createCustomValidation(phone2Input, phoneValidityChecks);
createCustomValidation(phone3Input, phoneValidityChecks);


function findCompaniesDevicesResponseHandler(response) {
    let oldRowElements = document.querySelectorAll('.device,.company');
    Array.from(oldRowElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let devicesUlElement = document.querySelector('.devices');
        createDropDownMenuElements(devicesUlElement, response.devices, 'device');
        let companyUlElement = document.querySelector('.companies');
        createDropDownMenuElements(companyUlElement, response.companies, 'company');

    }
}

function createDropDownMenuElements(root, elements, name) {
    for (const element of elements) {
        let liElement = document.createElement('li');
        liElement.classList.add(name);
        let linkElement = document.createElement('a');
        linkElement.classList.add('dropdown-item');
        linkElement.setAttribute('href', '');
        linkElement.setAttribute('data-id', element.id);
        linkElement.innerHTML = element.name;
        linkElement.addEventListener('click', dropDownMenuElementClickHandler);
        liElement.append(linkElement);
        root.append(liElement);
    }
}

function dropDownMenuElementClickHandler(event) {
    event.preventDefault();
    let currentElement = event.currentTarget;
    let inputElement = currentElement.parentElement.parentElement.parentElement.firstElementChild;
    inputElement.value = currentElement.innerHTML;
    inputElement.dataset.id = currentElement.dataset.id;
    checkInputField(inputElement, validatedNewOrderFormHandler, saveOrderButton);
}

function findClientsByPhoneHandler() {
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'find_clients_by_phone');
    searchParams.append('findPhoneNumber', findPhoneInput.value);
    sendPostFormQuery(controller, searchParams).then(response => findClientsByPhoneResponseHandler(response));
}

function findClientsByPhoneResponseHandler(response) {
    if (response != null && typeof response === 'object') {
        let clientsUlElement = document.querySelector('.clients');
        for (const client of response) {
            let liElement = document.createElement('li');
            liElement.classList.add('client');
            let clientLinkElement = document.createElement('a');
            clientLinkElement.classList.add('dropdown-item');
            clientLinkElement.setAttribute('href', '');
            clientLinkElement.setAttribute('data-id', client.userId);
            clientLinkElement.innerHTML = client.firstName + ' ' + client.secondName;
            clientLinkElement.addEventListener('click', dropDownMenuClientClickHandler);
            liElement.append(clientLinkElement);
            clientsUlElement.append(liElement);
        }
    }
    console.log('response received:' + response);
}

function dropDownMenuClientClickHandler(event) {
    event.preventDefault();
    //fill client data
}