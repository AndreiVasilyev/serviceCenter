// prepare variables
let localeSelectorElement = document.getElementById('locale_selector');

//set listeners after page loaded
document.addEventListener('DOMContentLoaded', addDocumentListeners);

// page loaded handler
function addDocumentListeners() {
    localeSelectorElement.addEventListener('change', changeLocaleHandler);
}

//locale selector handler
function changeLocaleHandler() {
    let locale = localeSelectorElement.value;
    let requestParameterElements = document.querySelectorAll('[name]');
    let requestParameters = Array.from(requestParameterElements)
        .filter(element => element.value != '')
        .reduce((queryParams, element) => {
            let name = element.getAttribute('name');
            let value = element.value;
            return queryParams + '&' + name + '=' + value;
        }, '');
    localStorage.setItem('currentEvent','changeLocale');
    if(localStorage.getItem('currentOrderNumber')!=null){
        localStorage.setItem('isDisplayResults','true');
    }
    let requestUrl = '/control?command=change_locale&locale=' + locale + requestParameters;
    location.href = requestUrl;
}

