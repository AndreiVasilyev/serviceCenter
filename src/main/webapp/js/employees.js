let employeeFilterInputElements = document.querySelectorAll('.employees-filter-input');
let employeeSortLinkElements = document.querySelectorAll('.employee-sort-link');
let clearEmployeeFilterButton = document.querySelector('.clear-employees-filter');
let modalRegisterEmployeeButton = document.querySelector('.register-employee');
let registerLoginInputElement = document.getElementById('register-user-login');
let registerRoleInputElement = document.getElementById('register-user-role');
let registerEmployeeButton = document.getElementById('register-employee-button');
let registerEmployeeInputElements = document.querySelectorAll('.register-new-employee');
let checkLoginButton = document.getElementById('check-login-button');

let registerLoginValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\wа-яА-Я@.]{3,20}$/gm);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('register-user-login-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let registerRoleValidityChecks = [
    {
        isInvalid: function (inputField) {
            let isInputFieldMatches = inputField.value.match(/^[\w]{1,50}$/);
            return !isInputFieldMatches;
        },
        invalidityMessage: function () {
            let loginErrorElement = document.getElementById('register-user-role-error');
            return loginErrorElement.dataset.errorMatch;
        }
    }];

let validatedRegisterFormHandler = function () {
    let isLoginCorrect = isFieldValid(registerLoginInputElement);
    if (isLoginCorrect) {
        checkLoginButton.removeAttribute('disabled');
    }
}

createCustomValidation(registerLoginInputElement, registerLoginValidityChecks);
createCustomValidation(registerRoleInputElement, registerRoleValidityChecks);


function employeesTabLoadHandler() {
    Array.from(employeeSortLinkElements).forEach(element => element.addEventListener('click', changeSortEmployeeHandler));
    Array.from(employeeFilterInputElements).forEach(element => element.addEventListener('input', changeEmployeeFilterInputHandler));
    clearEmployeeFilterButton.addEventListener('click', clearEmployeeFilterButtonHandler);
    modalRegisterEmployeeButton.addEventListener('click', openModalRegisterEmployeeHandler);
    getEmployeeDataFromServer();
}

function openModalRegisterEmployeeHandler() {
    checkLoginButton.addEventListener('click',checkLoginButtonHandler);
    Array.from(registerEmployeeInputElements).forEach(element => {
        element.addEventListener('blur', onBlurRegisterInputElementHandler);
        element.addEventListener('focus', onFocusRegisterInputElementHandler);
    });
}

function checkLoginButtonHandler(){
    let login=registerLoginInputElement.value;
    let controller = '/control';
    let params = new URLSearchParams();
    params.append('command', 'check_login');
    params.append('login', login);
    sendPostFormQuery(controller, params).then(response => checkOrderResponseHandler(response));
}

function checkOrderResponseHandler(){

}


function onBlurRegisterInputElementHandler() {
    checkInputField(this, validatedRegisterFormHandler, checkLoginButton);
}

function onFocusRegisterInputElementHandler() {
    registerEmployeeButton.setAttribute('disabled', 'disabled');
    this.classList.remove('is-invalid');
    this.classList.remove('is-valid');
}

function clearEmployeeFilterButtonHandler() {
    Array.from(employeeFilterInputElements).forEach(element => element.value = '');
    getEmployeeDataFromServer();
}

function getEmployeeDataFromServer() {
    employeeFilterInputAccess(false);
    let parameters = collectCurrentEmployeeParameters(employeeFilterInputElements);
    let controller = '/control?command=find_employees';
    sendPostJsonQuery(controller, parameters).then(response => findEmployeesResponseHandler(response));
}

function findEmployeesResponseHandler(response) {
    console.log("response OK " + response);
    let oldRowElements = document.querySelectorAll('.employee-row');
    Array.from(oldRowElements).forEach(element => element.remove());
    if (response != null && typeof response === 'object') {
        let tableBodyElement = document.querySelector('.employees-body');
        for (const employee of response) {
            let rowElement = document.createElement('tr');
            rowElement.classList.add('employee-row');
            let firstName = employee.firstName != null ? employee.firstName : '';
            let secondName = employee.secondName != null ? employee.secondName : '';
            let patronymic = employee.patronymic != null ? employee.patronymic : '';
            let postcode = employee.address != null && employee.address.postcode != null ? employee.address.postcode : '';
            let country = employee.address != null && employee.address.country != null ? employee.address.country : '';
            let state = employee.address != null && employee.address.state != null ? employee.address.state : '';
            let region = employee.address != null && employee.address.region != null ? employee.address.region : '';
            let city = employee.address != null && employee.address.city != null ? employee.address.city : '';
            let street = employee.address != null && employee.address.street != null ? employee.address.street : '';
            let houseNumber = employee.address != null && employee.address.houseNumber != null ? employee.address.houseNumber : '';
            let apartmentNumber = employee.address != null && employee.address.apartmentNumber != null ? employee.address.apartmentNumber : '';
            let phones = '';
            if (employee.phones != null && employee.phones.length > 0) {
                for (phone of employee.phones) {
                    phones = phones.concat(phone, ', ');
                }
                phones = phones.substr(0, phones.length - 2);
            }
            rowElement = appendTableCell(employee.id, rowElement);
            let cellElement = document.createElement('td');
            cellElement.classList.add('text-center', 'align-middle');
            let selectElement = document.querySelector('.role-select').cloneNode(true);
            selectElement.firstChild.nextSibling.remove();
            selectElement.classList.remove('employees-filter-input', 'role-select');
            selectElement.classList.add('role-selected');
            selectElement.removeAttribute('disabled');
            selectElement.value = employee.userRole;
            selectElement.addEventListener('input', changeEmployeeRoleHandler);
            cellElement.append(selectElement);
            rowElement.append(cellElement);
            rowElement = appendTableCell(secondName, rowElement);
            rowElement = appendTableCell(firstName, rowElement);
            rowElement = appendTableCell(patronymic, rowElement);
            rowElement = appendTableCell(employee.email, rowElement);
            rowElement = appendTableCell(postcode, rowElement);
            rowElement = appendTableCell(country, rowElement);
            rowElement = appendTableCell(state, rowElement);
            rowElement = appendTableCell(region, rowElement);
            rowElement = appendTableCell(city, rowElement);
            rowElement = appendTableCell(street, rowElement);
            rowElement = appendTableCell(houseNumber, rowElement);
            rowElement = appendTableCell(apartmentNumber, rowElement);
            rowElement = appendTableCell(phones, rowElement);
            tableBodyElement.append(rowElement);
        }
    }
    employeeFilterInputAccess(true);
}

function collectCurrentEmployeeParameters(elements) {
    let parameters = {};
    parameters['id'] = elements[0].value;
    parameters['userRole'] = elements[1].value;
    parameters['secondName'] = elements[2].value;
    parameters['firstName'] = elements[3].value;
    parameters['patronymic'] = elements[4].value;
    parameters['email'] = elements[5].value;
    parameters['postcode'] = elements[6].value;
    parameters['country'] = elements[7].value;
    parameters['state'] = elements[8].value;
    parameters['region'] = elements[9].value;
    parameters['city'] = elements[10].value;
    parameters['street'] = elements[11].value;
    parameters['houseNumber'] = elements[12].value;
    parameters['apartmentNumber'] = elements[13].value;
    parameters['phones'] = elements[14].value;
    let currentSortElements = Array.from(employeeSortLinkElements)
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

function employeeFilterInputAccess(accessibility) {
    Array.from(employeeFilterInputElements).forEach(element => {
        if (accessibility) {
            element.removeAttribute('disabled');
        } else {
            element.setAttribute('disabled', 'disabled');
        }
    });
}

function changeSortEmployeeHandler(event) {
    let currentElement = event.currentTarget.firstElementChild;
    let currentSortState = currentElement.dataset.sort;
    Array.from(employeeSortLinkElements).map(element => element.firstElementChild)
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
    getEmployeeDataFromServer();
}

function changeEmployeeFilterInputHandler() {
    clearTimeout(typingTimer);
    typingTimer = setTimeout(getEmployeeDataFromServer, typingTimerInterval);
}

function changeEmployeeRoleHandler(event) {
    let currentRoleElement = event.currentTarget;
    let currentRole = currentRoleElement.value;
    let currentEmployeeIdElement = currentRoleElement.parentElement.previousElementSibling;
    let currentEmployeeId = currentEmployeeIdElement.innerHTML;
    let controller = '/control';
    let searchParams = new URLSearchParams();
    searchParams.append('command', 'change_employee_role');
    searchParams.append('employeeId', currentEmployeeId);
    searchParams.append('employeeRole', currentRole);
    sendPostFormQuery(controller, searchParams).then(response => changeEmployeeRoleResponseHandler(response));
}

function changeEmployeeRoleResponseHandler(response) {
    if (response != null && typeof response == 'string') {
        let splitedResponse = response.split(':');
        if (splitedResponse.length > 1) {
            let changedUserId = splitedResponse[1];
            let currentRoleElement = document.getElementById('current-role');
            let currentUserId = currentRoleElement.dataset.userId;
            if (changedUserId == currentUserId) {
                location.href = '/control?command=goto_control_page';
            }
        }
    }
}