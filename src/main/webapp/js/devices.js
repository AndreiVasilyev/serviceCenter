let devicesFilterInputElements = document.querySelectorAll('.device-filter-input');
let devicesSortLinkElements = document.querySelectorAll('.device-sort-link');
let clearDevicesFilterButton = document.querySelector('.clear-devices-filter');
let deviceNameInputElement = document.getElementById('device-name-input');
let addDeviceButton = document.getElementById('add-device-button');
let newDeviceResultBlock = document.getElementById('alert-new-device-result');
let newDeviceResultMessageElement = document.getElementById('new-device-result-message');

let deviceNameValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^([a-zA-Zа-яА-Я]{3,20})([ -][a-zA-Zа-яА-Я]{1,20}){0,4}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('device-name-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

createCustomValidation(deviceNameInputElement, deviceNameValidityChecks);

let validatedNewDeviceFormHandler = function () {
    if (isFieldValid(deviceNameInputElement)) {
        addDeviceButton.removeAttribute('disabled');
    }
}

function devicesTabLoadHandler() {
    Array.from(devicesSortLinkElements).forEach(element => element.addEventListener('click', changeSortDevicesHandler));
    Array.from(devicesFilterInputElements).forEach(element => element.addEventListener('input', changeDevicesFilterInputHandler));
    clearDevicesFilterButton.addEventListener('click', clearDevicesFilterButtonHandler);
    deviceNameInputElement.classList.remove('is-valid', 'is-invalid');
    deviceNameInputElement.value = '';
    deviceNameInputElement.addEventListener('blur', onBlurNewDeviceInputHandler);
    deviceNameInputElement.addEventListener('focus', onFocusNewDeviceInputHandler);
    newDeviceResultBlock.classList.replace('d-flex', 'd-none');
    addDeviceButton.addEventListener('click', addDeviceButtonClickHandler);
    getDevicesDataFromServer();
}

function onBlurNewDeviceInputHandler() {
    checkInputField(this, validatedNewDeviceFormHandler, addDeviceButton);
}

function onFocusNewDeviceInputHandler() {
    newDeviceResultBlock.classList.replace('d-flex', 'd-none');
    addDeviceButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}

function addDeviceButtonClickHandler() {
    let parameters = {};
    parameters['name'] = deviceNameInputElement.value;
    let controller = '/control?command=add_new_device';
    sendPostJsonQuery(controller, parameters).then(response => addDeviceResponseHandler(response));
}

function addDeviceResponseHandler(response) {
    newDeviceResultBlock.classList.replace('d-none', 'd-flex');
    let splitResult = response.split(':');
    newDeviceResultMessageElement.innerHTML = splitResult[1];
    if (splitResult[0] === 'ok') {
        deviceNameInputElement.value = '';
        deviceNameInputElement.classList.remove('is-valid', 'is-invalid');
        addDeviceButton.setAttribute('disabled', 'disabled');
        getDevicesDataFromServer();
    } else {
        deviceNameInputElement.classList.add('is-invalid');
    }
}

function getDevicesDataFromServer() {
    devicesFilterInputAccess(false);
    let parameters = collectCurrentDevicesParameters(devicesFilterInputElements);
    let controller = '/control?command=find_devices';
    sendPostJsonQuery(controller, parameters).then(response => findDevicesResponseHandler(response));
}

function devicesFilterInputAccess(accessibility) {
    if (accessibility) {
        deviceNameInputElement.removeAttribute('disabled');
    } else {
        deviceNameInputElement.setAttribute('disabled', 'disabled');
    }
}

function collectCurrentDevicesParameters(elements) {
    let parameters = {};
    parameters['id'] = elements[0].value;
    parameters['name'] = elements[1].value;
    let currentSortElements = Array.from(devicesSortLinkElements)
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

function findDevicesResponseHandler(response) {
    let oldRowElements = document.querySelectorAll('.device-row');
    Array.from(oldRowElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let tableBodyElement = document.querySelector('.devices-body');
        for (const device of response) {
            let rowElement = document.createElement('tr');
            rowElement.classList.add('device-row');
            let id = device.id !== 0 ? device.id : '';
            let name = device.name != null ? device.name : '';
            rowElement = appendTableCell(id, rowElement);
            rowElement = appendTableCell(name, rowElement);
            rowElement = appendTableCell('', rowElement);
            tableBodyElement.append(rowElement);
        }
    }
    devicesFilterInputAccess(true);
}

function changeSortDevicesHandler(event) {
    let currentElement = event.currentTarget.firstElementChild;
    let currentSortState = currentElement.dataset.sort;
    Array.from(devicesSortLinkElements).map(element => element.firstElementChild)
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
    getDevicesDataFromServer();
}

function changeDevicesFilterInputHandler() {
    clearTimeout(typingTimer);
    typingTimer = setTimeout(getDevicesDataFromServer, typingTimerInterval);
}

function clearDevicesFilterButtonHandler() {
    Array.from(devicesFilterInputElements).forEach(element => element.value = '');
    getDevicesDataFromServer();
}