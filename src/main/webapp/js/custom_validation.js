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

/*
var companyNameValidityChecks = [
    {
        isInvalid : function(input) {
            return input.value.length == 1;
        },
        invalidityMessage : 'Название компании должно сожержать минимум 2 символа'
    },
    {
        isInvalid : function(input) {
            var illegalCharacters = input.value
                .match(/[^a-zA-Zа-яА-Я0-9. &!@#$%^_№"~':,/\|*)(-]/g);
            return illegalCharacters ? true : false;
        },
        invalidityMessage : 'Поле содержит недопустимые символы'
    } ];

var customerAddressValidityChecks = [ {
    isInvalid : function(input) {
        return input.value.length < 5;
    },
    invalidityMessage : 'Поле адреса  должно содержать минимум 5 символов'
}, {
    isInvalid : function(input) {
        var isLegalAddress = input.value.match(/[a-zA-Zа-яА-Я0-9. #№"':,/-]/g);
        return isLegalAddress ? false : true;
    },
    invalidityMessage : 'Поле адреса содержит недопустимые символы'
} ];

var phoneNumberValidityChecks = [
    {
        isInvalid : function(input) {
            return input.value.length < 18;
        },
        invalidityMessage : 'Поле обязательное к заполнению'
    },
    {
        isInvalid : function(input) {
            var isLegalPhone = input.value
                .match(/\+375\([1-9]{2}\) [1-9][0-9]{2}(-[0-9]{2}){2}/);
            return isLegalPhone ? false : true;
        },
        invalidityMessage : 'Недопустимый формат номера телефона'
    } ];

var commentValidityChecks = [ {
    isInvalid : function(input) {
        return input.value.length == 0;
    },
    invalidityMessage : 'Поле обязательное к заполнению'
}, {
    isInvalid : function(input) {
        return input.value.length < 20;
    },
    invalidityMessage : 'Сообщение должно содержать не менее 20 символов'
} ];

var addInfoValidityChecks = [ {
    isInvalid : function(input) {
        return input.value.length < 5 && input.value.length > 0;
    },
    invalidityMessage : 'Поле дополнительной информации  не может содержать менее 5 символов'
} ];

var deviceModelValidityChecks = [ {
    isInvalid : function(input) {
        return input.value.length < 2 && input.value.length > 0;
    },
    invalidityMessage : 'Поле модели  не может содержать менее 2-х символов'
} ];

var titleMessageEmailChecks = [ {
    isInvalid : function(input) {
        return input.value.length == 0;
    },
    invalidityMessage : 'Поле обязательное к заполнению'
}];
var messageEmailChecks = [ {
    isInvalid : function(input) {
        return input.value.length == 0;
    },
    invalidityMessage : 'Поле обязательное к заполнению'
}];
*/

