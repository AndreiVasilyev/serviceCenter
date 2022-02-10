//Check order form validation

//----- prepare variables for page elements

let orderTypeInput = document.getElementById('order-type');
let deviceInput = document.getElementById('order-device');
let companyInput = document.getElementById('order-company');
let modelInput = document.getElementById('order-model');
let serialInput = document.getElementById('order-serial');
let clientIdInput = document.getElementById('client-id');
let findPhoneInput = document.getElementById('find-phone');
let emailInput = document.getElementById('order-email');
let firstNameInput = document.getElementById('order-first-name');
let secondNameInput = document.getElementById('order-second-name');
let patronymicInput = document.getElementById('order-patronymic');
let phone1Input = document.getElementById('order-phone-1');
let phone2Input = document.getElementById('order-phone-2');
let phone3Input = document.getElementById('order-phone-3');
let countryInput = document.getElementById('order-country');
let postcodeInput = document.getElementById('order-postcode');
let stateInput = document.getElementById('order-state');
let regionInput = document.getElementById('order-region');
let cityInput = document.getElementById('order-city');
let streetInput = document.getElementById('order-street');
let houseInput = document.getElementById('order-house');
let apartmentInput = document.getElementById('order-apartment');
let noteInput = document.getElementById('order-note');
let saveOrderButton = document.getElementById('save-button');
let findPhoneButton = document.getElementById('find-phone-button');
let resetClientButton = document.getElementById('reset-client-button');
let inputElements = document.querySelectorAll('.add-order');

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
            let isInputFieldMatches = inputField.value.match(/^([a-zA-zа-яА-Я]{1,20}( [a-zA-zа-яА-Я]{1,20}){0,3})?$/);
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
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я -]{3,20})?$/);
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
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я -]{3,20})?$/);
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
            let isInputFieldMatches = inputField.value.match(/^\+\d{12}$/);
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
            let isInputFieldMatches = inputField.value.match(/^([a-zA-ZА-Яа-я]{3,20})?$/i);
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
            let isInputFieldMatches = inputField.value.match(/^(\+\d{12})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-phones-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let phoneRequiredValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^\+\d{12}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-phones-error');
            return loginErrorElement.dataset.errorRequired;
        }
    }];

let countryValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я- ]{3,30})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-country-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let postcodeValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^(\d{6})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-postcode-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let stateValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я\. ]{8,40})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-state-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let regionValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я\. -]{8,40})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-region-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let cityValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-Zа-яА-Я- ]{3,30}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-city-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let streetValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-Zа-яА-Я- \.]{5,40}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-street-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];
let houseValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\d]{1,4}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-house-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let apartmentValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\d]{1,4})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('client-apartment-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let noteValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я].*)?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('order-note-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

//----- prepare function to handle form when all fields validated

let validatedNewOrderFormHandler = function () {
    if (isFieldValid(findPhoneInput)) {
        findPhoneButton.removeAttribute('disabled');
    }
    let isFormValid = true;
    Array.from(inputElements).forEach(element => {
        if (element.id != 'find-phone' && element.id != 'locale_selector') {
            if (element.classList.contains('required')) {
                if (element.value == '' || !isFieldValid(element)) {
                    saveOrderButton.setAttribute('disabled', 'disabled');
                    isFormValid = false;
                }
            } else {
                if (isFieldInvalid(element)) {
                    saveOrderButton.setAttribute('disabled', 'disabled');
                    isFormValid = false;
                }
            }
        }
    });
    if (isFormValid) {
        saveOrderButton.removeAttribute('disabled');
    }

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
createCustomValidation(phone1Input, phoneRequiredValidityChecks);
createCustomValidation(phone2Input, phoneValidityChecks);
createCustomValidation(phone3Input, phoneValidityChecks);
createCustomValidation(countryInput, countryValidityChecks);
createCustomValidation(postcodeInput, postcodeValidityChecks);
createCustomValidation(stateInput, stateValidityChecks);
createCustomValidation(regionInput, regionValidityChecks);
createCustomValidation(cityInput, cityValidityChecks);
createCustomValidation(streetInput, streetValidityChecks);
createCustomValidation(houseInput, houseValidityChecks);
createCustomValidation(apartmentInput, apartmentValidityChecks);
createCustomValidation(noteInput, noteValidityChecks);

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
    let clientsUlElement = document.querySelector('.clients');
    clientsUlElement.innerHTML = '';
    if (response != null && !isEmpty(response)) {
        if (typeof response == 'object') {
            for (const client of response) {
                console.log('client: ' + client);
                let liElement = document.createElement('li');
                liElement.classList.add('client');
                let clientLinkElement = document.createElement('a');
                clientLinkElement.classList.add('dropdown-item');
                clientLinkElement.setAttribute('href', '');
                clientLinkElement.setAttribute('data-id', client.id);
                clientLinkElement.innerHTML = client.firstName + ' ' + client.secondName;
                clientLinkElement.addEventListener('click', dropDownMenuClientClickHandler);
                liElement.append(clientLinkElement);
                clientsUlElement.append(liElement);
            }
        } else {
            findPhoneInput.classList.add('is-invalid');
            let spanElement = document.createElement('span');
            spanElement.innerHTML = '-- invalid value --';
            spanElement.classList.add('ms-4');
            clientsUlElement.append(spanElement);
        }
    } else {
        let spanElement = document.createElement('span');
        spanElement.innerHTML = '-- not found --';
        spanElement.classList.add('ms-4');
        clientsUlElement.append(spanElement);
    }
}

function isEmpty(object) {
    for (let key in object) {
        return false;
    }
    return true;
}

function dropDownMenuClientClickHandler(event) {
    event.preventDefault();
    resetClientInput();
    let currentClientLink = event.currentTarget;
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'find_client_by_id');
    searchParams.append('userId', currentClientLink.dataset.id);
    sendPostFormQuery(controller, searchParams).then(response => findClientByIdResponseHandler(response));
}

function findClientByIdResponseHandler(response) {
    if (response != null && response !== '') {
        clientIdInput.value = response.id;
        fillClientCell(firstNameInput, response.firstName);
        fillClientCell(secondNameInput, response.secondName);
        fillClientCell(phone1Input, response.phones[0]);
        fillClientCell(cityInput, response.address.city);
        fillClientCell(streetInput, response.address.street);
        fillClientCell(houseInput, response.address.houseNumber);
        if (response.email != null && response.email !== '') {
            fillClientCell(emailInput, response.email);
        }
        if (response.patronymic != null && response.patronymic !== '') {
            fillClientCell(patronymicInput, response.patronymic);
        }
        if (response.phones.length > 1) {
            fillClientCell(phone2Input, response.phones[1]);
        }
        if (response.phones.length > 2) {
            fillClientCell(phone3Input, response.phones[2]);
        }
        if (response.address.country != null && response.address.country !== '') {
            countryInput.value = response.address.country;
            fillClientCell(countryInput, response.address.country);
        }
        if (response.address.postcode != null && response.address.postcode !== '') {
            fillClientCell(postcodeInput, response.address.postcode);
        }
        if (response.address.state != null && response.address.state !== '') {
            fillClientCell(stateInput, response.address.state);
        }
        if (response.address.region != null && response.address.region !== '') {
            fillClientCell(regionInput, response.address.region);
        }
        if (response.address.apartmentNumber != null && response.address.apartmentNumber !== '') {
            fillClientCell(apartmentInput, response.address.apartmentNumber);
        }
    }
}

function fillClientCell(inputField, value) {
    inputField.value = value;
    checkInputField(inputField, validatedNewOrderFormHandler, saveOrderButton);
}

function resetClientInput() {
    let clientInputElements = document.querySelectorAll('.client-input');
    Array.from(clientInputElements).forEach(element => {
        element.value = '';
        element.classList.remove('is-valid');
        element.classList.remove('is-invalid');
        element.parentElement.nextSibling.nextSibling.innerHTML = '';
    });
    clientIdInput.value = '';
}

function saveNewOrderHandler() {
    saveOrderButton.setAttribute('disabled', 'disabled');
    let parameters = collectnNewOrderData();
    let controller = '/control?command=save_new_order';
    sendPostJsonQuery(controller, parameters).then(response => saveNewOrderResponseHandler(response));
}

function collectnNewOrderData() {
    let parameters = {};
    parameters['orderNumber'] = orderTypeInput.value;
    parameters['deviceName'] = deviceInput.value;
    parameters['deviceId'] = deviceInput.dataset.id;
    parameters['companyName'] = companyInput.value;
    parameters['companyId'] = companyInput.dataset.id;
    parameters['model'] = modelInput.value;
    parameters['serial'] = serialInput.value;
    parameters['clientId'] = clientIdInput.value;
    parameters['email'] = emailInput.value;
    parameters['firstName'] = firstNameInput.value;
    parameters['secondName'] = secondNameInput.value;
    parameters['patronymic'] = patronymicInput.value;
    parameters['phoneFirst'] = phone1Input.value;
    parameters['phoneSecond'] = phone2Input.value;
    parameters['phoneThird'] = phone3Input.value;
    parameters['country'] = countryInput.value;
    parameters['postcode'] = postcodeInput.value;
    parameters['state'] = stateInput.value;
    parameters['region'] = regionInput.value;
    parameters['city'] = cityInput.value;
    parameters['street'] = streetInput.value;
    parameters['houseNumber'] = houseInput.value;
    parameters['apartmentNumber'] = apartmentInput.value;
    parameters['note'] = noteInput.value;
    return parameters;
}

function saveNewOrderResponseHandler(response) {
    console.log('saved: ' + response);
    if (response != null && typeof response == 'string') {
        let resultOperation = response.split(':')[0];
        let resultElement = document.querySelector('.save-order-result');
        let resultMessageElement = document.querySelector('.save-order-message');
        resultElement.classList.remove('d-none');
        resultElement.classList.add('d-flex');
        if (resultOperation == 'ok') {
            clearAddOrderInputElements();
        }
        resultMessageElement.innerHTML = response.split(':')[1];
    }
}

function clearAddOrderInputElements() {
    Array.from(inputElements).forEach(element => {
        element.value = '';
        element.classList.remove('is-valid');
    });
}