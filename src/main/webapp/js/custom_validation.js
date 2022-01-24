// Common custom validation code

//-----create custom validation object

function CustomValidation() {
    this.invalidityMessages = [];
    this.validityChecks = [];
}

CustomValidation.prototype = {
    addInvalidityMessage: function (message) {
        this.invalidityMessages.push(message);
    },
    getInvalidityMessages: function () {
        return this.invalidityMessages;
    },
    checkValidity: function (inputField) {
        for (let i = 0; i < this.validityChecks.length; i++) {
            let isInvalid = this.validityChecks[i].isInvalid(inputField);
            if (isInvalid) {
                this.addInvalidityMessage(this.validityChecks[i].invalidityMessage());
            }
        }
        localStorage.removeItem(inputField.name);
        localStorage.setItem(inputField.name, inputField.value);
    }
};

//-----function create custom validation for input field

function createCustomValidation(inputField, fieldChecks) {
    inputField.CustomValidation = new CustomValidation();
    inputField.CustomValidation.validityChecks = fieldChecks;
}

//-----function execute validation of field

function checkInputField(inputField, validatedFormHandler, submitButton) {
    let invalidityMessageElement = inputField.parentElement.nextSibling.nextSibling;
    inputField.CustomValidation.invalidityMessages = [];
    inputField.CustomValidation.checkValidity(inputField);
    invalidityMessageElement.innerHTML = '';
    inputField.classList.remove('is-invalid');
    inputField.classList.remove('is-valid');
    if (inputField.CustomValidation.invalidityMessages.length === 0) {
        inputField.classList.add('is-valid');
        validatedFormHandler();
    } else if (!inputField.hasAttribute('disabled')) {
        inputField.classList.add('is-invalid');
        let invalidityMessages = inputField.CustomValidation.getInvalidityMessages();
        for (let i = 0; i < invalidityMessages.length; i++) {
            invalidityMessageElement.innerHTML += '<p class="d-block mb-0">\u2022\u0020'
                + invalidityMessages[i] + '</p>';
        }
        submitButton.setAttribute('disabled', 'disabled');
    }
}

//----- function check if field validated

function isFieldValid(inputField) {
    return inputField.classList.contains('is-valid');
}

