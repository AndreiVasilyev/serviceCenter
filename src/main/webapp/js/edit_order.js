//Check order form validation

//----- prepare variables for page elements

let orderIdEditInput = document.getElementById('edit-id');
let orderNumberEditInput = document.getElementById('edit-number');
let statusEditInput = document.getElementById('edit-status');
let creationDateEditInput = document.getElementById('edit-creation-date');
let acceptedEmployeeEditInput = document.getElementById('edit-accepted-employee');
let deviceEditInput = document.getElementById('edit-device');
let companyEditInput = document.getElementById('edit-company');
let modelEditInput = document.getElementById('edit-model');
let serialEditInput = document.getElementById('edit-serial');
let clientIdEditInput = document.getElementById('edit-client-id');
let addressIdEditInput = document.getElementById('edit-address-id');
let findPhoneEditInput = document.getElementById('edit-find-phone');
let emailEditInput = document.getElementById('edit-email');
let firstNameEditInput = document.getElementById('edit-first-name');
let secondNameEditInput = document.getElementById('edit-second-name');
let patronymicEditInput = document.getElementById('edit-patronymic');
let phone1EditInput = document.getElementById('edit-phone-1');
let phone2EditInput = document.getElementById('edit-phone-2');
let phone3EditInput = document.getElementById('edit-phone-3');
let countryEditInput = document.getElementById('edit-country');
let postcodeEditInput = document.getElementById('edit-postcode');
let stateEditInput = document.getElementById('edit-state');
let regionEditInput = document.getElementById('edit-region');
let cityEditInput = document.getElementById('edit-city');
let streetEditInput = document.getElementById('edit-street');
let houseEditInput = document.getElementById('edit-house');
let apartmentEditInput = document.getElementById('edit-apartment');
let completedEmployeeEditInput = document.getElementById('edit-completed-employee');
let completionDateEditInput = document.getElementById('edit-completion-date');
let issueDateEditInput = document.getElementById('edit-issue-date');
let priceInfoIdEditInput = document.getElementById('edit-price-info-id');
let repairLevelEditInput = document.getElementById('edit-repair-level');
let workPriceEditInput = document.getElementById('edit-work-price');
let partsCostEditInput = document.getElementById('edit-parts-cost');
let totalCostEditInput = document.getElementById('edit-total-cost');
let workDescriptionEditInput = document.getElementById('edit-work-description');
let noteEditInput = document.getElementById('edit-note');
let sparePartsEditInput = document.getElementById('edit-spare-parts');
let findPartEditInput = document.getElementById('edit-find-part');
let findPartEditButton = document.getElementById('edit-find-part-button');
let removePartEditButton = document.getElementById('edit-remove-part-button');
let saveOrderEditButton = document.getElementById('edit-save-button');
let findPhoneEditButton = document.getElementById('edit-find-phone-button');
let resetClientEditButton = document.getElementById('edit-reset-client-button');
let inputEditElements = document.querySelectorAll('.edit-order');
let errorMessageElements = document.querySelectorAll('div[data-error-match]');

//----- prepare validity checks for form fields

let creationDateValidityChecks = [
    {
        isInvalid: function (inputField) {
            return inputField.value == '';
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-creation-date-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let acceptedEmployeeValidityChecks = [
    {
        isInvalid: function (inputField) {
            return inputField.value == '';
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-accepted-employee-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let deviceEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-zа-яА-Я]{3,20})([ -][a-zA-zа-яА-Я]{1,20}){0,4}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-device-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let companyEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-zа-яА-Я]{1,20}( [a-zA-zа-яА-Я]{1,20}){0,3})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-company-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let modelEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я \-\+]{3,20})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-model-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let serialEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я -]{3,20})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-serial-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let phoneFindEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^\+\d{12}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-phone-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let emailAddressEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([^\s@]+@[^\s@]+\.[^\s@]+)?$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('edit-email-error');
            return emailErrorElement.dataset.errorMatch;
        }
    }];

let firstNameEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-ZА-Яа-я-]{3,20}$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('edit-names-error');
            return emailErrorElement.dataset.errorFirst;
        }
    }];

let secondNameEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-ZА-Яа-я-]{3,40}$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('edit-names-error');
            return emailErrorElement.dataset.errorSecond;
        }
    }];

let patronymicEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-ZА-Яа-я]{3,20})?$/i);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let emailErrorElement = document.getElementById('edit-names-error');
            return emailErrorElement.dataset.errorPatronymic;
        }
    }];

let phoneEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^(\+\d{12})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-phones-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let phoneRequiredEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^\+\d{12}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-phones-error');
            return loginErrorElement.dataset.errorRequired;
        }
    }];

let countryEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я- ]{3,30})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-country-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let postcodeEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^(\d{6})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-postcode-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let stateEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я\. ]{8,40})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-state-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let regionEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я\. -]{8,40})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-region-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let cityEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-Zа-яА-Я- ]{3,30}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-city-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let streetEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[a-zA-Zа-яА-Я- \.]{5,40}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-street-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];
let houseEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\d]{1,4}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-house-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let apartmentEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\d]{1,4})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-apartment-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let noteEditValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я].*)?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-note-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let completedEmployeeValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\d]{1,4})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-completed-employee-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];
let completionDateValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\d]{4}-[\d]{2}-[\d]{2}T[\d]{2}:[\d]{2})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-completion-date-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let issueDateValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\d]{4}-[\d]{2}-[\d]{2}T[\d]{2}:[\d]{2})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-issue-date-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let repairLevelValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\w]{1,50})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-repair-level-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let workDescriptionValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([\wа-яА-Я].{3,200})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('edit-work-description-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

//----- prepare function to handle form when all fields validated

let validatedEditOrderFormHandler = function () {
    if (isFieldValid(findPhoneEditInput)) {
        findPhoneEditButton.removeAttribute('disabled');
    }
    let isFormValid = true;
    Array.from(inputEditElements).forEach(element => {
        if (element.id != 'edit-find-phone' && element.id != 'locale_selector') {
            if (!element.hasAttribute('disabled') && element.classList.contains('is-invalid')) {
                saveOrderEditButton.setAttribute('disabled', 'disabled');
                isFormValid = false;
            }
        }
    });
    if (isFormValid) {
        saveOrderEditButton.removeAttribute('disabled');
    }
}

//----- creating custom validation for form fields

createCustomValidation(creationDateEditInput, creationDateValidityChecks);
createCustomValidation(acceptedEmployeeEditInput, acceptedEmployeeValidityChecks);
createCustomValidation(deviceEditInput, deviceEditValidityChecks);
createCustomValidation(companyEditInput, companyEditValidityChecks);
createCustomValidation(modelEditInput, modelEditValidityChecks);
createCustomValidation(serialEditInput, serialEditValidityChecks);
createCustomValidation(findPhoneEditInput, phoneFindEditValidityChecks);
createCustomValidation(emailEditInput, emailAddressEditValidityChecks);
createCustomValidation(firstNameEditInput, firstNameEditValidityChecks);
createCustomValidation(secondNameEditInput, secondNameEditValidityChecks);
createCustomValidation(patronymicEditInput, patronymicEditValidityChecks);
createCustomValidation(phone1EditInput, phoneRequiredEditValidityChecks);
createCustomValidation(phone2EditInput, phoneEditValidityChecks);
createCustomValidation(phone3EditInput, phoneEditValidityChecks);
createCustomValidation(countryEditInput, countryEditValidityChecks);
createCustomValidation(postcodeEditInput, postcodeEditValidityChecks);
createCustomValidation(stateEditInput, stateEditValidityChecks);
createCustomValidation(regionEditInput, regionEditValidityChecks);
createCustomValidation(cityEditInput, cityEditValidityChecks);
createCustomValidation(streetEditInput, streetEditValidityChecks);
createCustomValidation(houseEditInput, houseEditValidityChecks);
createCustomValidation(apartmentEditInput, apartmentEditValidityChecks);
createCustomValidation(noteEditInput, noteEditValidityChecks);
createCustomValidation(completedEmployeeEditInput, completedEmployeeValidityChecks);
createCustomValidation(completionDateEditInput, completionDateValidityChecks);
createCustomValidation(issueDateEditInput, issueDateValidityChecks);
createCustomValidation(repairLevelEditInput, repairLevelValidityChecks);
createCustomValidation(workDescriptionEditInput, workDescriptionValidityChecks);

function closeEditModalHandler() {
    Array.from(inputEditElements).forEach(element => {
        element.value = '';
        element.classList.remove('is-valid');
        element.classList.remove('is-invalid');
    });
    Array.from(errorMessageElements).forEach(element => element.innerHTML = '');
    let resultElement = document.querySelector('.update-order-result');
    resultElement.classList.add('d-none');
    resultElement.classList.remove('d-flex');
    getDataFromServer();
}

function editOrderClickHandler(event) {
    event.preventDefault();
    let currentLinkElement = event.currentTarget;
    let currentOrderId = currentLinkElement.dataset.id;
    let controllerWithParam = '/control?command=find_all_selectable_items';
    sendPostJsonQuery(controllerWithParam, {}).then(response => findSelectableItemsResponseHandler(response, currentOrderId));
    findPhoneEditButton.addEventListener('click', findEditClientsByPhoneHandler);
    resetClientEditButton.addEventListener('click', resetEditClientInput);
    saveOrderEditButton.addEventListener('click', saveChangedOrderHandler);
    removePartEditButton.addEventListener('click', removePartClickHandler);
    findPartEditButton.addEventListener('click', findPartClickHandler);
    let inputElements = document.querySelectorAll('.edit-order');
    Array.from(inputElements).forEach(element => {
        element.addEventListener('blur', onBlurEditInputFieldHandler);
        element.addEventListener('focus', onFocusEditInputFieldHandler);
    })
    completedEmployeeEditInput.addEventListener('change', changeCompletedEmployeeHandler);
    repairLevelEditInput.addEventListener('change', changeRepairLevelHandler);
    completionDateEditInput.addEventListener('change', changeCompletionDateHandler);
    issueDateEditInput.addEventListener('change', changeIssueDateHandler);
}

function changeIssueDateHandler() {
    if (issueDateEditInput.value == '') {
        statusEditInput.value = 'CLOSED';
    } else {
        statusEditInput.value = 'ISSUED';
    }
}

function changeCompletionDateHandler() {
    if (completionDateEditInput.value == '') {
        issueDateEditInput.value = '';
        issueDateEditInput.setAttribute('disabled', 'disabled');
        statusEditInput.value = 'IN_PROGRESS';
    } else {
        let currentRole = document.getElementById('current-role').dataset.role;
        if (currentRole == 'ADMIN') {
            issueDateEditInput.removeAttribute('disabled');
        }
        statusEditInput.value = 'CLOSED';
    }
}

function changeRepairLevelHandler() {
    if (repairLevelEditInput.value == '') {
        completionDateEditInput.value = '';
        completionDateEditInput.setAttribute('disabled', 'disabled');
        issueDateEditInput.value = '';
        issueDateEditInput.setAttribute('disabled', 'disabled');
        workPriceEditInput.value = '';
        priceInfoIdEditInput.value = '';
        statusEditInput.value = 'IN_PROGRESS';
        calculatePartsCost();
    } else {
        completionDateEditInput.removeAttribute('disabled');
    }
}

function changeCompletedEmployeeHandler() {
    if (completedEmployeeEditInput.value == '') {
        repairLevelEditInput.value = '';
        repairLevelEditInput.setAttribute('disabled', 'disabled');
        completionDateEditInput.value = '';
        completionDateEditInput.setAttribute('disabled', 'disabled');
        issueDateEditInput.value = '';
        issueDateEditInput.setAttribute('disabled', 'disabled');
        workPriceEditInput.value = '';
        workDescriptionEditInput.value = '';
        workDescriptionEditInput.setAttribute('disabled', 'disabled');
        priceInfoIdEditInput.value = '';
        statusEditInput.value = 'ACCEPTED';
        sparePartsEditInput.innerHTML = '';
        sparePartsEditInput.setAttribute('disabled', 'disabled');
        findPartEditInput.setAttribute('disabled', 'disabled');
        findPartEditButton.setAttribute('disabled', 'disabled');
        removePartEditButton.setAttribute('disabled', 'disabled');
        calculatePartsCost();
    } else {
        repairLevelEditInput.removeAttribute('disabled');
        workDescriptionEditInput.removeAttribute('disabled');
        sparePartsEditInput.removeAttribute('disabled');
        findPartEditInput.removeAttribute('disabled');
        findPartEditButton.removeAttribute('disabled');
        removePartEditButton.removeAttribute('disabled');
        if (statusEditInput.value == 'ACCEPTED') {
            statusEditInput.value = 'IN_PROGRESS';
        }
    }
}


function findSelectableItemsResponseHandler(response, currentOrderId) {
    let oldSelectbleItemElements = document.querySelectorAll('.edit-device,.edit-company,.edit-employee,.edit-level,.edit-part');
    Array.from(oldSelectbleItemElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let devicesUlElement = document.querySelector('.edit-devices');
        createDropDownMenuElements(devicesUlElement, response.devices, 'edit-device');
        let companyUlElement = document.querySelector('.edit-companies');
        createDropDownMenuElements(companyUlElement, response.companies, 'edit-company');
        createEmployeeOptionElements(acceptedEmployeeEditInput, response.employees, 'edit-employee');
        createEmployeeOptionElements(completedEmployeeEditInput, response.employees, 'edit-employee');
        createLevelOptionElements(repairLevelEditInput, response.levels, 'edit-level');
    }
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'find_order_by_id');
    searchParams.append('orderId', currentOrderId);
    sendPostFormQuery(controller, searchParams).then(response => findOrderByIdResponseHandler(response));
}


//-----input field OnBlur event handler

function onBlurEditInputFieldHandler() {
    if (!this.classList.contains('no-validate')) {
        checkInputField(this, validatedEditOrderFormHandler, saveOrderEditButton);
    }
}

//-----input field OnFocus event handler

function onFocusEditInputFieldHandler() {
    if (!this.classList.contains('no-validate')) {
        saveOrderEditButton.setAttribute('disabled', 'disabled');
        this.classList.remove('is-invalid');
        this.classList.remove('is-valid');
    }
    let resultElement = document.querySelector('.update-order-result');
    resultElement.classList.add('d-none');
    resultElement.classList.remove('d-flex');
}

function findEditClientsByPhoneHandler() {
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'find_clients_by_phone');
    searchParams.append('findPhoneNumber', findPhoneEditInput.value);
    sendPostFormQuery(controller, searchParams).then(response => findEditClientsByPhoneResponseHandler(response));
}

function findEditClientsByPhoneResponseHandler(response) {
    let clientsUlElement = document.querySelector('.edit-clients');
    clientsUlElement.innerHTML = '';
    if (response != null && !isEmpty(response)) {
        if (typeof response == 'object') {
            for (const client of response) {
                let liElement = document.createElement('li');
                liElement.classList.add('client');
                let clientLinkElement = document.createElement('a');
                clientLinkElement.classList.add('dropdown-item');
                clientLinkElement.setAttribute('href', '');
                clientLinkElement.setAttribute('data-id', client.id);
                clientLinkElement.innerHTML = client.firstName + ' ' + client.secondName;
                clientLinkElement.addEventListener('click', foundClientClickHandler);
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

function foundClientClickHandler(event) {
    event.preventDefault();
    clearEditClientInput();
    let currentClientLink = event.currentTarget;
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'find_client_by_id');
    searchParams.append('userId', currentClientLink.dataset.id);
    sendPostFormQuery(controller, searchParams).then(response => findEditClientByIdResponseHandler(response));
}

function findEditClientByIdResponseHandler(response) {
    if (response != null && response !== '') {
        clientIdEditInput.value = response.id;
        fillEditClientCell(firstNameEditInput, response.firstName);
        fillEditClientCell(secondNameEditInput, response.secondName);
        fillEditClientCell(phone1EditInput, response.phones[0]);
        fillEditClientCell(cityEditInput, response.address.city);
        fillEditClientCell(streetEditInput, response.address.street);
        fillEditClientCell(houseEditInput, response.address.houseNumber);
        if (response.email != null && response.email !== '') {
            fillEditClientCell(emailEditInput, response.email);
        }
        if (response.patronymic != null && response.patronymic !== '') {
            fillEditClientCell(patronymicEditInput, response.patronymic);
        }
        if (response.phones.length > 1) {
            fillEditClientCell(phone2EditInput, response.phones[1]);
        }
        if (response.phones.length > 2) {
            fillEditClientCell(phone3EditInput, response.phones[2]);
        }
        if (response.address.country != null && response.address.country !== '') {
            countryEditInput.value = response.address.country;
            fillEditClientCell(countryEditInput, response.address.country);
        }
        if (response.address.postcode != null && response.address.postcode !== '') {
            fillEditClientCell(postcodeEditInput, response.address.postcode);
        }
        if (response.address.state != null && response.address.state !== '') {
            fillEditClientCell(stateEditInput, response.address.state);
        }
        if (response.address.region != null && response.address.region !== '') {
            fillEditClientCell(regionEditInput, response.address.region);
        }
        if (response.address.apartmentNumber != null && response.address.apartmentNumber !== '') {
            fillEditClientCell(apartmentEditInput, response.address.apartmentNumber);
        }
    }
}

function fillEditClientCell(inputField, value) {
    inputField.value = value;
    checkInputField(inputField, validatedEditOrderFormHandler, saveOrderEditButton);
}

function resetEditClientInput() {
    let clientInputElements = document.querySelectorAll('.edit-client');
    Array.from(clientInputElements).forEach(element => {
        element.value = element.dataset.oldValue;
        if (!element.classList.contains('no-validate')) {
            checkInputField(element, validatedEditOrderFormHandler, saveOrderEditButton);
        }
    });
}

function clearEditClientInput() {
    let clientInputElements = document.querySelectorAll('.edit-client');
    Array.from(clientInputElements).forEach(element => element.value = "");
}

function removePartClickHandler() {
    let selectedElementIndex = sparePartsEditInput.selectedIndex;
    if (selectedElementIndex >= 0) {
        sparePartsEditInput.options[selectedElementIndex].remove();
        if (sparePartsEditInput.options.length == 0) {
            removePartEditButton.setAttribute('disabled', 'disabled');
        }
        calculatePartsCost();
    }
}

function findPartClickHandler() {
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'find_parts_by_param');
    searchParams.append('findPartParam', findPartEditInput.value);
    sendPostFormQuery(controller, searchParams).then(response => findPartsByParamResponseHandler(response));
}

function findPartsByParamResponseHandler(response) {
    let partsUlElement = document.querySelector('.edit-parts');
    partsUlElement.innerHTML = '';
    if (response != null && !isEmpty(response)) {
        for (const part of response) {
            let liElement = document.createElement('li');
            liElement.classList.add('edit-find-part');
            let partLinkElement = document.createElement('a');
            partLinkElement.classList.add('dropdown-item');
            partLinkElement.setAttribute('href', '');
            partLinkElement.setAttribute('data-id', part.id);
            partLinkElement.setAttribute('data-part-number', part.partNumber);
            partLinkElement.setAttribute('data-name', part.name);
            partLinkElement.setAttribute('data-description', part.description);
            partLinkElement.setAttribute('data-cost', part.cost);
            partLinkElement.innerHTML = part.partNumber + ' ' + part.name;
            partLinkElement.addEventListener('click', foundPartClickHandler);
            liElement.append(partLinkElement);
            partsUlElement.append(liElement);
        }
    } else {
        let spanElement = document.createElement('span');
        spanElement.innerHTML = '-- not found --';
        spanElement.classList.add('ms-4');
        partsUlElement.append(spanElement);
    }
}

function foundPartClickHandler(event) {
    event.preventDefault();
    let currentLink = event.currentTarget;
    let optionElement = document.createElement('option');
    optionElement.classList.add('edit-part');
    optionElement.value = currentLink.dataset.id;
    optionElement.setAttribute('data-cost', currentLink.dataset.cost);
    optionElement.innerHTML = currentLink.dataset.partNumber + '&nbsp;&nbsp;&nbsp;&nbsp;' + currentLink.dataset.name + '&nbsp;&nbsp;&nbsp;&nbsp;' + currentLink.dataset.description + '&nbsp;&nbsp;&nbsp;&nbsp;' + currentLink.dataset.cost;
    sparePartsEditInput.append(optionElement);
    calculatePartsCost();
    if (sparePartsEditInput.children.length == 1) {
        removePartEditButton.removeAttribute('disabled');
    }
}

function createEmployeeOptionElements(selectElement, values, className) {
    for (const value of values) {
        let optionElement = document.createElement('option');
        optionElement.classList.add(className);
        optionElement.value = value.id.toString();
        optionElement.innerHTML = value.firstName + ' ' + value.secondName;
        selectElement.append(optionElement);
    }
}

function createLevelOptionElements(selectElement, values, className) {
    for (const value of values) {
        let optionElement = document.createElement('option');
        optionElement.classList.add(className);
        optionElement.innerHTML = value;
        selectElement.append(optionElement);
    }
}

function findOrderByIdResponseHandler(response) {
    let companyEdit = response.company != null ? response.company.name : '';
    let companyIdEdit = response.company != null ? response.company.id : 0;
    let modelEdit = response.model != null ? response.model : '';
    let serialEdit = response.serialNumber != null ? response.serialNumber : '';
    let patronymicEdit = response.client.patronymic != null ? response.client.patronymic : '';
    let phone2Edit = response.client.phones.length > 1 ? response.client.phones[1] : '';
    let phone3Edit = response.client.phones.length > 2 ? response.client.phones[2] : '';
    let emailEdit = response.client.email != null ? response.client.email : '';
    let countryEdit = response.client.address.country != null ? response.client.address.country : '';
    let postcodeEdit = response.client.address.postcode != 0 ? response.client.address.postcode : '';
    let stateEdit = response.client.address.state != null ? response.client.address.state : '';
    let regionEdit = response.client.address.region != null ? response.client.address.region : '';
    let apartmentEdit = response.client.address.apartmentNumber != 0 ? response.client.address.apartmentNumber : '';
    let completedEmployee = response.completedEmployee != null ? response.completedEmployee.id.toString() : '';
    let completionDate = response.completionDate != null ? serverDateToBrowserDate(response.completionDate, completionDateEditInput) : '';
    let issueDate = response.issueDate != null ? serverDateToBrowserDate(response.issueDate, issueDateEditInput) : '';
    let repairLevel = response.workPrice != null ? response.workPrice.repairLevel : '';
    let repairCost = response.workPrice != null ? response.workPrice.repairCost : '';
    let workDescription = response.workDescription != null ? response.workDescription : '';
    let note = response.note != null ? response.note : '';
    orderIdEditInput.value = response.id;
    orderNumberEditInput.value = response.orderNumber;
    statusEditInput.value = response.orderStatus;
    creationDateEditInput.value = serverDateToBrowserDate(response.creationDate, creationDateEditInput);
    acceptedEmployeeEditInput.value = response.acceptedEmployee.id.toString();
    deviceEditInput.value = response.device.name;
    deviceEditInput.dataset.id = response.device.id;
    companyEditInput.value = companyEdit;
    companyEditInput.dataset.id = companyIdEdit;
    modelEditInput.value = modelEdit;
    serialEditInput.value = serialEdit;
    clientIdEditInput.value = response.client.id;
    clientIdEditInput.dataset.oldValue = response.client.id;
    firstNameEditInput.value = response.client.firstName;
    firstNameEditInput.dataset.oldValue = response.client.firstName;
    secondNameEditInput.value = response.client.secondName;
    secondNameEditInput.dataset.oldValue = response.client.secondName;
    patronymicEditInput.value = patronymicEdit;
    patronymicEditInput.dataset.oldValue = patronymicEdit;
    phone1EditInput.value = response.client.phones[0];
    phone1EditInput.dataset.oldValue = response.client.phones[0];
    phone2EditInput.value = phone2Edit;
    phone2EditInput.dataset.oldValue = phone2Edit;
    phone3EditInput.value = phone3Edit;
    phone3EditInput.dataset.oldValue = phone3Edit;
    addressIdEditInput.value = response.client.address.id;
    addressIdEditInput.dataset.oldValue = response.client.address.id;
    emailEditInput.value = emailEdit;
    emailEditInput.dataset.oldValue = emailEdit;
    countryEditInput.value = countryEdit;
    countryEditInput.dataset.oldValue = countryEdit;
    postcodeEditInput.value = postcodeEdit;
    postcodeEditInput.dataset.oldValue = postcodeEdit;
    stateEditInput.value = stateEdit;
    stateEditInput.dataset.oldValue = stateEdit;
    regionEditInput.value = regionEdit;
    regionEditInput.dataset.oldValue = regionEdit;
    cityEditInput.value = response.client.address.city;
    cityEditInput.dataset.oldValue = response.client.address.city;
    streetEditInput.value = response.client.address.street;
    streetEditInput.dataset.oldValue = response.client.address.street;
    houseEditInput.value = response.client.address.houseNumber;
    houseEditInput.dataset.oldValue = response.client.address.houseNumber;
    apartmentEditInput.value = apartmentEdit;
    apartmentEditInput.dataset.oldValue = apartmentEdit;
    completedEmployeeEditInput.value = completedEmployee;
    completionDateEditInput.value = completionDate;
    issueDateEditInput.value = issueDate;
    repairLevelEditInput.value = repairLevel;
    repairLevelEditInput.addEventListener('change', updateWorkPriceInput);
    workPriceEditInput.value = repairCost;
    calculateTotalCost();
    workDescriptionEditInput.value = workDescription;
    noteEditInput.value = note;
    if (response.spareParts != null) {
        if (!sparePartsEditInput.hasAttribute('disabled')) {
            removePartEditButton.removeAttribute('disabled');
        }
        createPartOptionElements(sparePartsEditInput, response.spareParts, 'edit-part');
        calculatePartsCost();
    }
    if (completedEmployee == '') {
        changeCompletedEmployeeHandler();
    } else if (repairLevel = '') {
        changeRepairLevelHandler();
    }
}

function createPartOptionElements(selectElement, parts, className) {
    for (const part of parts) {
        let optionElement = document.createElement('option');
        optionElement.classList.add(className);
        optionElement.value = part.id;
        optionElement.setAttribute('data-cost', part.cost);
        optionElement.innerHTML = part.partNumber + '&nbsp;&nbsp;&nbsp;&nbsp;' + part.name + '&nbsp;&nbsp;&nbsp;&nbsp;' + part.description + '&nbsp;&nbsp;&nbsp;&nbsp;' + part.cost;
        selectElement.append(optionElement);
    }
}

function calculateTotalCost() {
    if (partsCostEditInput.value != '' || workPriceEditInput.value != '') {
        if (partsCostEditInput.value == '') {
            totalCostEditInput.value = Number.parseInt(workPriceEditInput.value);
        } else if (workPriceEditInput.value == '') {
            totalCostEditInput.value = Number.parseInt(partsCostEditInput.value);
        } else {
            totalCostEditInput.value = Number.parseInt(partsCostEditInput.value) + Number.parseInt(workPriceEditInput.value);
        }
    } else {
        totalCostEditInput.value = '';
    }
}

function calculatePartsCost() {
    let partElements = document.querySelectorAll('.edit-part');
    let partsCost = Array.from(partElements).reduce((sum, elem) => sum + Number.parseInt(elem.dataset.cost), 0);
    partsCostEditInput.value = partsCost;
    if (workPriceEditInput.value != '') {
        totalCostEditInput.value = partsCost + Number.parseInt(workPriceEditInput.value);
    } else {
        totalCostEditInput.value = partsCost;
    }
}

function serverDateToBrowserDate(serverDate, inputElement) {
    let splitedServerDate = serverDate.split(/[ :]+/);
    let browserDate = splitedServerDate[2].concat('-', splitedServerDate[1], '-', splitedServerDate[0], 'T', splitedServerDate[3], ':', splitedServerDate[4]);
    inputElement.dataset.sec = splitedServerDate[5];
    return browserDate;
}

function browserDateToServerDate(browserDate, seconds) {
    let splitedBrowserDate = browserDate.split(/[-T:]+/);
    if (seconds == '') {
        seconds = '00';
    }
    let serverDate = splitedBrowserDate[2].concat(' ', splitedBrowserDate[1], ' ', splitedBrowserDate[0], ' ', splitedBrowserDate[3], ':', splitedBrowserDate[4], ':', seconds);
    return serverDate;
}

function updateWorkPriceInput() {
    let currentDeviceId = deviceEditInput.dataset.id;
    let currentRepairLevel = repairLevelEditInput.value;
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'find_work_cost');
    searchParams.append('deviceIdParam', currentDeviceId);
    searchParams.append('repairLevelParam', currentRepairLevel);
    sendPostFormQuery(controller, searchParams).then(response => {
        workPriceEditInput.value = response;
        calculateTotalCost();
    });


}

function saveChangedOrderHandler() {
    saveOrderEditButton.setAttribute('disabled', 'disabled');
    let parameters = collectEditedOrderData();
    let controller = '/control?command=update_order';
    sendPostJsonQuery(controller, parameters).then(response => updateOrderResponseHandler(response));
}

function collectEditedOrderData() {
    let parameters = {};
    parameters['id'] = orderIdEditInput.value;
    parameters['orderNumber'] = orderNumberEditInput.value;
    parameters['orderStatus'] = statusEditInput.value;
    parameters['creationDate'] = browserDateToServerDate(creationDateEditInput.value, creationDateEditInput.dataset.sec);
    parameters['acceptedEmployeeId'] = acceptedEmployeeEditInput.value;
    parameters['deviceName'] = deviceEditInput.value;
    parameters['deviceId'] = deviceEditInput.dataset.id;
    parameters['companyName'] = companyEditInput.value;
    parameters['companyId'] = companyEditInput.dataset.id;
    parameters['model'] = modelEditInput.value;
    parameters['serial'] = serialEditInput.value;
    parameters['clientId'] = clientIdEditInput.value;
    parameters['addressId'] = addressIdEditInput.value;
    parameters['email'] = emailEditInput.value;
    parameters['firstName'] = firstNameEditInput.value;
    parameters['secondName'] = secondNameEditInput.value;
    parameters['patronymic'] = patronymicEditInput.value;
    parameters['phoneFirst'] = phone1EditInput.value;
    parameters['phoneSecond'] = phone2EditInput.value;
    parameters['phoneThird'] = phone3EditInput.value;
    parameters['country'] = countryEditInput.value;
    parameters['postcode'] = postcodeEditInput.value;
    parameters['state'] = stateEditInput.value;
    parameters['region'] = regionEditInput.value;
    parameters['city'] = cityEditInput.value;
    parameters['street'] = streetEditInput.value;
    parameters['houseNumber'] = houseEditInput.value;
    parameters['apartmentNumber'] = apartmentEditInput.value;
    parameters['note'] = noteEditInput.value;
    parameters['completedEmployeeId'] = completedEmployeeEditInput.value;
    parameters['completionDate'] = completionDateEditInput.value != '' ? browserDateToServerDate(completionDateEditInput.value, completionDateEditInput.dataset.sec) : '';
    parameters['issueDate'] = issueDateEditInput.value != '' ? browserDateToServerDate(issueDateEditInput.value, issueDateEditInput.dataset.sec) : '';
    parameters['workPriceId'] = priceInfoIdEditInput.value;
    parameters['repairLevel'] = repairLevelEditInput.value;
    parameters['workDescription'] = workDescriptionEditInput.value;
    if (sparePartsEditInput.options.length > 0) {
        parameters['spareParts'] = '';
        for (var i = 0; i < sparePartsEditInput.options.length; i++) {
            parameters['spareParts'] += sparePartsEditInput.options[i].value + ' ';
        }
    } else {
        parameters['spareParts'] = '';
    }
    return parameters;
}

function updateOrderResponseHandler(response) {
    if (response != null && typeof response == 'string') {
        let resultOperation = response.split(':')[0];
        let resultElement = document.querySelector('.update-order-result');
        let resultMessageElement = document.querySelector('.update-order-message');
        resultElement.classList.remove('d-none');
        resultElement.classList.add('d-flex');
        resultMessageElement.innerHTML = response.split(':')[1];
    }
}
