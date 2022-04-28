let devicesSelectElement = document.getElementById('device-name-select');
let diagnosticInputElement = document.getElementById('edit-diagnostic-input');
let maintenanceInputElement = document.getElementById('edit-maintenance-input');
let repairLevel1InputElement = document.getElementById('edit-repair-level1-input');
let repairLevel2InputElement = document.getElementById('edit-repair-level2-input');
let repairLevel3InputElement = document.getElementById('edit-repair-level3-input');
let technicalConclusionInputElement = document.getElementById('edit-technical-conclusion-input');
let pricesInputElements = document.querySelectorAll('.edit-price');
let savePricesButton = document.getElementById('save-price-button');
let findPricesResultBlock = document.getElementById('alert-find-prices-result');
let findPricesResultMessageElement = document.getElementById('find-prices-result-message');
let savePricesResultBlock = document.getElementById('alert-save-prices-result');
let savePricesResultMessageElement = document.getElementById('save-prices-result-message');

let costValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^\d+(\.\d{2})?$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let costErrorElement = document.getElementById('edit-diagnostic-error');
            return costErrorElement.dataset.errorMatch;
        }
    }];

createCustomValidation(diagnosticInputElement, costValidityChecks);
createCustomValidation(maintenanceInputElement, costValidityChecks);
createCustomValidation(repairLevel1InputElement, costValidityChecks);
createCustomValidation(repairLevel2InputElement, costValidityChecks);
createCustomValidation(repairLevel3InputElement, costValidityChecks);
createCustomValidation(technicalConclusionInputElement, costValidityChecks);

let validatedPricesFormHandler = function () {
    let isFormCorrect = true;
    Array.from(pricesInputElements).forEach(element => {
        isFormCorrect = !!(isFieldValid(element) || isFieldDefault(element));
    })
    if (isFormCorrect) {
        savePricesButton.removeAttribute('disabled');
    }
}

function isFieldDefault(inputField) {
    return !inputField.classList.contains('is-valid') && !inputField.classList.contains('is-invalid');
}

function pricesTabLoadHandler() {
    loadDevices();
    devicesSelectElement.addEventListener('change', changeDeviceEventHandler);
    Array.from(pricesInputElements).forEach(element => {
        element.addEventListener('blur', onBlurPricesInputHandler);
        element.addEventListener('focus', onFocusPricesInputHandler);
    })
    savePricesButton.addEventListener('click', onClickSavePricesButtonHandler);
    findPricesResultBlock.classList.replace('d-flex', 'd-none');
    savePricesResultBlock.classList.replace('d-flex', 'd-none');
}

function onClickSavePricesButtonHandler() {
    let parameters = {};
    parameters['device'] = devicesSelectElement.value;
    parameters['diagnostic'] = diagnosticInputElement.value;
    parameters['maintenance'] = maintenanceInputElement.value;
    parameters['repairLevel1'] = repairLevel1InputElement.value;
    parameters['repairLevel2'] = repairLevel2InputElement.value;
    parameters['repairLevel3'] = repairLevel3InputElement.value;
    parameters['technicalConclusion'] = technicalConclusionInputElement.value;
    let controller = '/control?command=save_prices_by_device';
    sendPostJsonQuery(controller, parameters).then(response => savePricesByDeviceResponseHandler(response));
}

function savePricesByDeviceResponseHandler(response) {
    let splitResult = response.split(':');
    savePricesResultMessageElement.innerHTML = splitResult[1];
    if (splitResult[0] === 'ok') {
        Array.from(pricesInputElements).forEach(element => element.classList.remove('is-valid', 'is-invalid'));
        savePricesButton.setAttribute('disabled', 'disabled');
        devicesSelectElement.dispatchEvent(new Event("change"));
    } else {
        Array.from(pricesInputElements).forEach(element => element.classList.replace('is-valid', 'is-invalid'));
    }
    savePricesResultBlock.classList.replace('d-none', 'd-flex');
}

function onBlurPricesInputHandler() {
    checkInputField(this, validatedPricesFormHandler, savePricesButton);
    if (this.classList.contains('is-valid') && !this.value.toString().includes('.')) {
        this.value = this.value.toString().concat('.00');
    }
}

function onFocusPricesInputHandler(event) {
    findPricesResultBlock.classList.replace('d-flex', 'd-none');
    savePricesResultBlock.classList.replace('d-flex', 'd-none');
    savePricesButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}

function loadDevices() {
    let controller = '/control?command=find_all_devices';
    sendPostJsonQuery(controller, {}).then(response => findAllDevicesResponseHandler(response));
}

function findAllDevicesResponseHandler(response) {
    if (response != null && typeof response === 'object') {
        for (const device of response) {
            let optionElement = document.createElement('option');
            optionElement.value = device.id;
            optionElement.innerHTML = device.name;
            devicesSelectElement.append(optionElement);
        }
    }
}

function changeDeviceEventHandler() {
    findPricesResultBlock.classList.replace('d-flex', 'd-none');
    savePricesResultBlock.classList.replace('d-flex', 'd-none');
    let currentDeviceId = this.value;
    if (currentDeviceId !== '0') {
        let controller = '/control';
        let searchParams = new URLSearchParams();
        searchParams.append('command', 'find_prices_by_device');
        searchParams.append('deviceIdParam', currentDeviceId);
        sendPostFormQuery(controller, searchParams).then(response => findPricesByDeviceResponseHandler(response));
    } else {
        resetPricesInputElements();
    }
}

function resetPricesInputElements() {
    Array.from(pricesInputElements).forEach(element => {
        element.value = '0.00';
        element.setAttribute('disabled', 'disabled');
        element.dataset.id = '0';
        element.classList.remove('is-valid', 'is-invalid');
    });
    savePricesButton.setAttribute('disabled', 'disabled');
}

function findPricesByDeviceResponseHandler(response) {
    if (response != null && typeof response === 'object') {
        resetPricesInputElements();
        Array.from(pricesInputElements).forEach(element => element.removeAttribute('disabled'));
        for (const price of response) {
            let currentPrice = price.repairCost.toString();
            if (!currentPrice.includes('.')) {
                currentPrice = currentPrice.concat('.00');
            }
            switch (price.repairLevel) {
                case 'DIAGNOSTIC':
                    diagnosticInputElement.value = currentPrice;
                    diagnosticInputElement.dataset.id = price.id;
                    break;
                case 'MAINTENANCE':
                    maintenanceInputElement.value = currentPrice;
                    maintenanceInputElement.dataset.id = price.id;
                    break;
                case 'REPAIR_LEVEL_1':
                    repairLevel1InputElement.value = currentPrice;
                    repairLevel1InputElement.dataset.id = price.id;
                    break;
                case 'REPAIR_LEVEL_2':
                    repairLevel2InputElement.value = currentPrice;
                    repairLevel2InputElement.dataset.id = price.id;
                    break;
                case 'REPAIR_LEVEL_3':
                    repairLevel3InputElement.value = currentPrice;
                    repairLevel3InputElement.dataset.id = price.id;
                    break;
                case 'TECHNICAL_CONCLUSION':
                    technicalConclusionInputElement.value = currentPrice;
                    technicalConclusionInputElement.dataset.id = price.id;
                    break;
            }
        }
    } else {
        findPricesResultBlock.classList.replace('d-none', 'd-flex');
        let splitResult = response.split(':');
        findPricesResultMessageElement.innerHTML = splitResult[1];
    }
}