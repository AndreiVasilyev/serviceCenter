// prepare variables
let localeSelectorElement = document.getElementById('locale_selector');
let clearableLinkElements = document.querySelectorAll('.clearable');

//set listeners after page loaded
document.addEventListener('DOMContentLoaded', addDocumentListeners);

// page loaded handler
function addDocumentListeners() {
    localeSelectorElement.addEventListener('change', changeLocaleHandler);
    clearableLinkElements.forEach(element => element.addEventListener('click', clickLinkHandler));
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
    let requestUrl = '/control?command=change_locale&locale=' + locale + requestParameters;
    location.href = requestUrl;
}

//click link handler(clear local storage)
function clickLinkHandler() {
    localStorage.clear();
}