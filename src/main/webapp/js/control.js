let currentActiveTab = document.querySelector('.tab-pane.fade.show.active');
let typingTimer;
let typingTimerInterval = 1000;
let clearFilterButton = document.querySelector('.clear-filter');
let newOrderButton = document.querySelector('.new-order');
let filterInputElements = document.querySelectorAll('.filter-input');
let sortLinkElements = document.querySelectorAll('.sort-link');
let editModalElement = document.getElementById('editOrderModal');
let pagination = document.querySelector('.pagination');
let paginationBlock = document.querySelector('.pagination-block');
let firstPage = document.querySelector('.first');
let previousPage = document.querySelector('.previous');
let nextPage = document.querySelector('.next');
let lastPage = document.querySelector('.last');

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
    //close edit modal window listener
    editModalElement.addEventListener('hidden.bs.modal', closeEditModalHandler);
    //pagination click listeners
    firstPage.addEventListener('click', firstPageClickHandler);
    previousPage.addEventListener('click', previousPageClickHandler);
    nextPage.addEventListener('click', nextPageClickHandler);
    lastPage.addEventListener('click', lastPageClickHandler);
    //get init data from server
    getDataFromServer();
}

function firstPageClickHandler(event) {
    event.preventDefault();
    pagination.dataset.isPaginate = 'true';
    pagination.dataset.currentPage = 1;
    getDataFromServer();
}

function previousPageClickHandler(event) {
    event.preventDefault();
    pagination.dataset.isPaginate = 'true';
    pagination.dataset.currentPage = +pagination.dataset.currentPage - 1;
    getDataFromServer();
}

function nextPageClickHandler(event) {
    event.preventDefault();
    pagination.dataset.isPaginate = 'true';
    pagination.dataset.currentPage = +pagination.dataset.currentPage + 1;
    getDataFromServer();
}

function lastPageClickHandler(event) {
    event.preventDefault();
    pagination.dataset.isPaginate = 'true';
    pagination.dataset.currentPage = Math.ceil(+pagination.dataset.totalOrders / 10);
    getDataFromServer();
}

function getDataFromServer() {
    filterInputAccess(false);
    let parameters = collectCurrentParametersState(filterInputElements);
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
    if (pagination.dataset.isPaginate == 'true') {
        pagination.dataset.isPaginate = '';
    } else {
        pagination.dataset.currentPage = 1;
    }
    parameters['pageNumber'] = pagination.dataset.currentPage;
    return parameters;
}

function findOrdersResponseHandler(response) {
    let oldRowElements = document.querySelectorAll('.order-row');
    Array.from(oldRowElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let tableBodyElement = document.querySelector('tbody');
        for (const orderObject of response.orders) {
            let rowElement = document.createElement('tr');
            rowElement.classList.add('order-row');
            let model = orderObject.model != null ? orderObject.model : '';
            let serial = orderObject.serialNumber != null ? orderObject.serialNumber : '';
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
            let orderStatus = orderObject.orderStatus;
            let currentRole = document.getElementById('current-role').dataset.role;
            let orderActionElement = createOrderActionCell(orderObject.id, orderStatus, currentRole);
            rowElement.append(orderActionElement);
            rowElement = appendTableCell(orderObject.orderNumber, rowElement);
            rowElement = appendTableCell(orderStatus, rowElement);
            rowElement = appendTableCell(orderObject.creationDate, rowElement);
            rowElement = appendTableCell(clientValue, rowElement);
            rowElement = appendTableCell(orderObject.device.name, rowElement);
            rowElement = appendTableCell(orderObject.company.name, rowElement);
            rowElement = appendTableCell(model, rowElement);
            rowElement = appendTableCell(serial, rowElement);
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
                cellElement.classList.add('px-3', 'align-middle');
                cellElement.append(partsElement);
                rowElement.append(cellElement);
            } else {
                rowElement = appendTableCell('', rowElement);
                rowElement = appendTableCell('', rowElement);
            }
            tableBodyElement.append(rowElement);
        }
        paginationBlock.classList.remove('d-none');
        pagination.dataset.totalOrders = response.totalOrdersQuantity;
        pagination.dataset.currentPage = response.currentPage;
        if (response.totalOrdersQuantity < 11) {
            paginationBlock.classList.add('d-none');
        } else {
            let totalPages = Math.ceil(+response.totalOrdersQuantity / 10);
            document.querySelector('.current-page').innerHTML = response.currentPage;
            document.querySelector('.total-pages').innerHTML = totalPages;
            let pageLinkElements = document.querySelectorAll('.page-link');
            Array.from(pageLinkElements).forEach(element => element.parentElement.classList.remove('disabled'));
            if (+response.currentPage == 1) {
                firstPage.parentElement.classList.add('disabled');
                previousPage.parentElement.classList.add('disabled');
            }
            if (+response.currentPage == totalPages) {
                lastPage.parentElement.classList.add('disabled');
                nextPage.parentElement.classList.add('disabled');
            }
        }
        let tooltipElements = document.querySelectorAll('[data-toggle="tooltip"]');
        Array.from(tooltipElements).map(function (tooltipElements) {
            return new bootstrap.Tooltip(tooltipElements)
        })
        let editLinkElements = document.querySelectorAll('.order-edit');
        Array.from(editLinkElements).forEach(element => element.addEventListener('click', editOrderClickHandler));
        let removeLinkElements = document.querySelectorAll('.order-remove');
        Array.from(removeLinkElements).forEach(element => element.addEventListener('click', removeOrderClickHandler));
        let takeToWorkLinkElements = document.querySelectorAll('.order-take-to-work');
        Array.from(takeToWorkLinkElements).forEach(element => element.addEventListener('click', takeToWorkOrderClickHandler));
        let issueLinkElements = document.querySelectorAll('.order-issue');
        Array.from(issueLinkElements).forEach(element => element.addEventListener('click', issueOrderClickHandler));

    }
    filterInputAccess(true);
}

function issueOrderClickHandler(event) {
    event.preventDefault();
    let currentLinkElement = event.currentTarget;
    let currentOrderId = currentLinkElement.dataset.id;
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'issue_order');
    searchParams.append('orderId', currentOrderId);
    sendPostFormQuery(controller, searchParams).then(response => issueOrderResponseHandler(response));
}

function takeToWorkOrderClickHandler(event) {
    event.preventDefault();
    let currentLinkElement = event.currentTarget;
    let currentOrderId = currentLinkElement.dataset.id;
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'take_order_to_work');
    searchParams.append('orderId', currentOrderId);
    sendPostFormQuery(controller, searchParams).then(response => takeToWorkOrderResponseHandler(response));
}

function issueOrderResponseHandler(response) {
    if (response != null && typeof response == 'string') {
        getDataFromServer();
    }
}

function takeToWorkOrderResponseHandler(response) {
    if (response != null && typeof response == 'string') {
        getDataFromServer();
    }
}

function removeOrderClickHandler(event) {
    event.preventDefault();
    let currentLinkElement = event.currentTarget;
    let currentOrderId = currentLinkElement.dataset.id;
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'remove_order_by_id');
    searchParams.append('orderId', currentOrderId);
    sendPostFormQuery(controller, searchParams).then(response => removeOrderByIdResponseHandler(response));
}

function removeOrderByIdResponseHandler(response) {
    if (response != null && typeof response == 'string') {
        getDataFromServer();
    }
}


function createOrderActionCell(id, status, role) {
    let tdElement = document.createElement('td');
    tdElement.classList.add('order-action', 'px-3', 'align-middle');
    if (role == 'ENGINEER' && status == 'ACCEPTED') {
        let takeToWorkLink = document.createElement('a');
        takeToWorkLink.classList.add('text-warning', 'me-2', 'order-take-to-work');
        takeToWorkLink.setAttribute('href', '');
        takeToWorkLink.setAttribute('data-id', id);
        takeToWorkLink.setAttribute('data-toggle', 'tooltip');
        takeToWorkLink.setAttribute('data-placement', 'top');
        takeToWorkLink.setAttribute('title', 'взять в работу');
        let handElement = document.createElement('i');
        handElement.classList.add('fa', 'fa-hand-lizard-o');
        takeToWorkLink.append(handElement);
        tdElement.append(takeToWorkLink);
    }
    let editLink = document.createElement('a');
    editLink.classList.add('text-warning', 'me-2', 'order-edit');
    editLink.setAttribute('href', '');
    editLink.setAttribute('data-bs-toggle', 'modal');
    editLink.setAttribute('data-bs-target', '#editOrderModal');
    editLink.setAttribute('data-id', id);
    editLink.setAttribute('data-toggle', 'tooltip');
    editLink.setAttribute('data-placement', 'top');
    editLink.setAttribute('title', 'изменить');
    let pencilElement = document.createElement('i');
    pencilElement.classList.add('fa', 'fa-pencil');
    editLink.append(pencilElement);
    tdElement.append(editLink);
    if (role != 'ENGINEER' && status == 'CLOSED') {
        let issueOrderLink = document.createElement('a');
        issueOrderLink.classList.add('text-success', 'me-2', 'order-issue');
        issueOrderLink.setAttribute('href', '');
        issueOrderLink.setAttribute('data-id', id);
        issueOrderLink.setAttribute('data-toggle', 'tooltip');
        issueOrderLink.setAttribute('data-placement', 'top');
        issueOrderLink.setAttribute('title', 'выдать');
        let outdoorElement = document.createElement('i');
        outdoorElement.classList.add('fa', 'fa-sign-out');
        issueOrderLink.append(outdoorElement);
        tdElement.append(issueOrderLink);
    }
    if (role = 'ADMIN') {
        let removeLink = document.createElement('a');
        removeLink.classList.add('text-danger', 'me-2', 'order-remove');
        removeLink.setAttribute('href', '');
        removeLink.setAttribute('data-id', id);
        removeLink.setAttribute('data-toggle', 'tooltip');
        removeLink.setAttribute('data-placement', 'top');
        removeLink.setAttribute('title', 'удалить');
        let trashElement = document.createElement('i');
        trashElement.classList.add('fa', 'fa-trash-o');
        removeLink.append(trashElement);
        tdElement.append(removeLink);
    }
    return tdElement;
}

function createDropdownPartsList(spareParts) {
    let divElement = document.createElement('div');
    divElement.classList.add('btn-group', 'dropstart');
    let buttonElement = document.createElement('button');
    buttonElement.classList.add('btn', 'btn-success', 'dropdown-toggle', 'btn-sm');
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
    cellElement.classList.add('text-center', 'align-middle');
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
    let inputElements = document.querySelectorAll('.add-order');
    Array.from(inputElements).forEach(element => {
        element.addEventListener('blur', onBlurInputFieldHandler);
        element.addEventListener('focus', onFocusInputFieldHandler);
    })
    findPhoneButton.addEventListener('click', findClientsByPhoneHandler);
    resetClientButton.addEventListener('click', resetClientInput);
    saveOrderButton.addEventListener('click', saveNewOrderHandler);
    let closeButtons = document.querySelectorAll('.close-save-order');
    Array.from(closeButtons).forEach(element => element.addEventListener('click', closeButtonClickHandler));
}

//-----close add new order modal window event handler

function closeButtonClickHandler() {
    clearAddOrderInputElements();
    getDataFromServer();
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
    let resultElement = document.querySelector('.save-order-result');
    resultElement.classList.add('d-none');
    resultElement.classList.remove('d-flex');
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