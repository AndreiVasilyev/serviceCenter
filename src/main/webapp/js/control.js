let currentActiveTab = document.querySelector('.tab-pane.fade.show.active');
let typingTimer;
let typingTimerInterval = 1000;
let clearFilterButton = document.querySelector('.clear-filter');
let newOrderButton = document.querySelector('.new-order');
let filterInputElements = document.querySelectorAll('.filter-input');
let sortLinkElements = document.querySelectorAll('.sort-link');


//Set listeners after page load/unload

document.addEventListener('DOMContentLoaded', loadDocumentHandler);


//-----page loaded handler

function loadDocumentHandler() {
    //change locale listener
    localeSelectorElement.addEventListener('change', changeLocaleHandler);
    //change current tab listener
    let tabSwitcherElements = document.querySelectorAll('button[data-bs-toggle]');
    Array.from(tabSwitcherElements).forEach(element => element.addEventListener('shown.bs.tab', tabSwitcherShowHandler));
    //change sort order listener
    Array.from(sortLinkElements).forEach(element => element.addEventListener('click', changeSortOrderHandler))
    //change filter value listener
    Array.from(filterInputElements).forEach(element => element.addEventListener('input', changeFilterInputHandler));
    //clear filter button listener
    clearFilterButton.addEventListener('click', clearFilterInputHandler);
    //create new order button listener
    newOrderButton.addEventListener('click', addNewOrderHandler);
    getDataFromServer();
}

function getDataFromServer() {
    filterInputAccess(false);
    let parameters = {};
    parameters = collectCurrentParametersState(filterInputElements);
    let controller = '/control?command=find_orders';
    sendPostJsonQuery(controller, parameters).then(response => findOrdersResponseHandler(response));
}

function collectCurrentParametersState(elements) {
    let parameters = {};
    parameters['orderNumber'] = elements[0].value;
    parameters['orderStatus'] = elements[1].value;
    parameters['creationDate'] = elements[2].value;
    parameters['client'] = elements[3].value;
    parameters['device'] = elements[4].value;
    parameters['company'] = elements[5].value;
    parameters['model'] = elements[6].value;
    parameters['serialNumber'] = elements[7].value;
    parameters['acceptedEmployee'] = elements[8].value;
    parameters['completedEmployee'] = elements[9].value;
    parameters['completionDate'] = elements[10].value;
    parameters['issueDate'] = elements[11].value;
    parameters['workDescription'] = elements[12].value;
    parameters['repairLevel'] = elements[13].value;
    parameters['repairCost'] = elements[14].value;
    parameters['note'] = elements[15].value;
    let currentSortElements = Array.from(sortLinkElements)
        .map(element => element.firstElementChild)
        .filter(element => element.dataset.sort !== '');
    if (currentSortElements != null && currentSortElements.length > 0) {
        parameters['sortByName'] = currentSortElements[0].dataset.column;
        parameters['sortDirection'] = currentSortElements[0].dataset.sort;
    } else {
        parameters['sortByName'] = '';
        parameters['sortDirection'] = '';
    }
    parameters['pageNumber'] = '1';
    return parameters;
}

function findOrdersResponseHandler(response) {
    let oldRowElements = document.querySelectorAll('.order-row');
    Array.from(oldRowElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let tableBodyElement = document.querySelector('tbody');
        for (const orderObject of response) {
            let rowElement = document.createElement('tr');
            rowElement.classList.add('order-row');
            let clientValue = orderObject.client.firstName + ' ' + orderObject.client.secondName;
            let acceptedEmployeeValue = orderObject.acceptedEmployee.firstName + ' ' + orderObject.acceptedEmployee.secondName;
            let completedEmployee = orderObject.completedEmployee != null ? orderObject.completedEmployee : '';
            let completedEmployeeValue = completedEmployee != '' ? completedEmployee.firstName + ' ' + completedEmployee.secondName : '';
            let completionDate = orderObject.completionDate != null ? orderObject.completionDate : '';
            let issueDate = orderObject.issueDate != null ? orderObject.issueDate : '';
            let workDescription = orderObject.workDescription != null ? orderObject.workDescription : '';
            let repairLevel = orderObject.workPrice != null ? orderObject.workPrice.repairLevel : '';
            let repairCost = orderObject.workPrice != null ? orderObject.workPrice.repairCost : '';
            let note = orderObject.note != null ? orderObject.note : '';
            rowElement = appendTableCell(orderObject.orderNumber, rowElement);
            rowElement = appendTableCell(orderObject.orderStatus, rowElement);
            rowElement = appendTableCell(orderObject.creationDate, rowElement);
            rowElement = appendTableCell(clientValue, rowElement);
            rowElement = appendTableCell(orderObject.device.name, rowElement);
            rowElement = appendTableCell(orderObject.company.name, rowElement);
            rowElement = appendTableCell(orderObject.model, rowElement);
            rowElement = appendTableCell(orderObject.serialNumber, rowElement);
            rowElement = appendTableCell(acceptedEmployeeValue, rowElement);
            rowElement = appendTableCell(completedEmployeeValue, rowElement);
            rowElement = appendTableCell(completionDate, rowElement);
            rowElement = appendTableCell(issueDate, rowElement);
            rowElement = appendTableCell(workDescription, rowElement);
            rowElement = appendTableCell(repairLevel, rowElement);
            rowElement = appendTableCell(repairCost, rowElement);
            rowElement = appendTableCell(note, rowElement);
            if (orderObject.spareParts != null && orderObject.spareParts.length > 0) {
                let partsSum = orderObject.spareParts.map(item => item.cost)
                    .reduce((sum, current) => sum + current, 0);
                rowElement = appendTableCell(partsSum, rowElement);
                let partsElement = createDropdownPartsList(orderObject.spareParts);
                let cellElement = document.createElement('td');
                cellElement.classList.add('mx-2');
                cellElement.append(partsElement);
                rowElement.append(cellElement);
            } else {
                rowElement = appendTableCell('', rowElement);
                rowElement = appendTableCell('', rowElement);
            }
            let orderActionElement = createOrderActionCell()
            rowElement.append(orderActionElement);
            tableBodyElement.append(rowElement);
        }
    }
    filterInputAccess(true);
}

function createOrderActionCell() {
    let tdElement = document.createElement('td');
    tdElement.classList.add('order-action');
    let editLink = document.createElement('a');
    editLink.classList.add('text-warning', 'me-2', 'order-edit');
    editLink.setAttribute('href', '');
    let removeLink = document.createElement('a');
    removeLink.classList.add('text-danger', 'me-2', 'order-remove');
    removeLink.setAttribute('href', '');
    let pencilElement = document.createElement('i');
    pencilElement.classList.add('fa', 'fa-pencil');
    let trashElement = document.createElement('i');
    trashElement.classList.add('fa', 'fa-trash-o');
    editLink.append(pencilElement);
    removeLink.append(trashElement);
    tdElement.append(editLink);
    tdElement.append(removeLink);
    return tdElement;
}

function createDropdownPartsList(spareParts) {
    let divElement = document.createElement('div');
    divElement.classList.add('btn-group', 'dropstart');
    let buttonElement = document.createElement('button');
    buttonElement.classList.add('btn', 'btn-success', 'dropdown-toggle');
    buttonElement.setAttribute('type', 'button');
    buttonElement.setAttribute('data-bs-toggle', 'dropdown');
    buttonElement.innerHTML = 'Запчасти';
    let tableElement = document.createElement('table');
    tableElement.classList.add('dropdown-menu');
    for (const part of spareParts) {
        let trElement = document.createElement('tr');
        let tdPartNumber = document.createElement('td');
        tdPartNumber.classList.add('px-2');
        tdPartNumber.innerHTML = part.partNumber;
        let tdName = document.createElement('td');
        tdName.classList.add('px-2');
        tdName.innerHTML = part.name;
        let tdDescription = document.createElement('td');
        tdDescription.classList.add('px-2');
        tdDescription.innerHTML = part.description;
        let tdCost = document.createElement('td');
        tdCost.classList.add('px-2');
        tdCost.innerHTML = part.cost;
        trElement.append(tdPartNumber);
        trElement.append(tdName);
        trElement.append(tdDescription);
        trElement.append(tdCost);
        tableElement.append(trElement);
    }
    divElement.append(buttonElement);
    divElement.append(tableElement);
    return divElement;
}

function appendTableCell(cellValue, rowElement) {
    let cellElement = document.createElement('td');
    cellElement.innerHTML = cellValue;
    rowElement.append(cellElement);
    return rowElement;
}

function tabSwitcherShowHandler(event) {
    currentActiveTab = document.querySelector('.tab-pane.fade.show.active');
}

function clearFilterInputHandler() {
    Array.from(filterInputElements).forEach(element => element.value = '');
    getDataFromServer();
}

function addNewOrderHandler() {
    let dropdownInputElements = document.querySelectorAll('.dropdown-input');
    Array.from(dropdownInputElements).forEach(element => element.addEventListener('input', () => element.dataset.id = '0'));
    let controller = '/control?command=find_all_companies_devices';
    sendPostJsonQuery(controller, {}).then(response => findCompaniesDevicesResponseHandler(response));
    let inputElements = document.querySelectorAll('.form-control,.form-select');
    Array.from(inputElements).forEach(element => {
        element.addEventListener('blur', onBlurInputFieldHandler);
        element.addEventListener('focus', onFocusInputFieldHandler);
    })
    findPhoneButton.addEventListener('click', findClientsByPhoneHandler);

    //add listeners to button and other elements
}

//-----input field OnBlur event handler

function onBlurInputFieldHandler() {
    checkInputField(this, validatedNewOrderFormHandler, saveOrderButton);
}

//-----input field OnFocus event handler

function onFocusInputFieldHandler() {
    saveOrderButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}

function changeSortOrderHandler(event) {
    let currentElement = event.currentTarget.firstElementChild;
    let currentSortState = currentElement.dataset.sort;
    Array.from(sortLinkElements).map(element => element.firstElementChild)
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
    getDataFromServer();
}

function changeFilterInputHandler() {
    clearTimeout(typingTimer);
    typingTimer = setTimeout(getDataFromServer, typingTimerInterval);
}

function filterInputAccess(accessibility) {
    Array.from(filterInputElements).forEach(element => {
        if (accessibility) {
            element.removeAttribute('disabled');
        } else {
            element.setAttribute('disabled', 'disabled');
        }
    });
}