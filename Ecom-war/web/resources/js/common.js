/*alert sans titre :D*/
/*window.alert = function(msg){
    var iframe = document.createElement("IFRAME");
    iframe.setAttribute("src", 'data:text/plain,');
    document.documentElement.appendChild(iframe);
    window.frames[0].window.alert(msg);
    iframe.parentNode.removeChild(iframe);
}*/


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validateConnexion() {
    var text = "";
    var valid = true;
    if (!$("[id*=':login']").val().match(/^[\w\-]+(\.[\w\-]+)*@[\w\-]+(\.[\w\-]+)*\.[\w\-]{2,4}$/)) {
        //$("[id*='login']").after('<p>Veuillez saisir une adresse email valide en tant qu\'identifiant</p>');
        text += "Veuillez saisir une adresse email valide en tant qu'identifiant \n";
        valid = false;
    }
    if ($("[id*=':password']").val() == '' || $("[id*=':password']").val().length < 5) {
        //$("[id*='login']").after('<p>Veuillez saisir une adresse email valide en tant qu\'identifiant</p>');
        text += "Votre mot de passe doit comporter au minimum 6 caractères \n";
        valid = false;
    } 
    if (!$("[id*=':password']").val().match(/^(?=.*\d)(?=.*[a-zA-Z]).{6,12}$/)) {
        text += "Votre mot de passe doit comporter au minimum un chiffre une lettre et 4-10 caractères additionnels (chiffre ou lettre)\n";
        valid = false;
    }
    

    if (valid) {
        return true;
    } else {
        alert(text);
        return false;
    }
}

function validateContact() {
    var text = "";
    var valid = true;
    if (!$("[id*='form-contact:Email']").val().match(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)) {
        text += "Veuillez saisir une adresse mail valide.\n";
        valid = false;
    }
    if ($("[id*='form-contact:Titre']").val() == "") {
        text += "Veuillez saisir un titre.\n";
        valid = false;
    }
    if (valid) {
        return true;
    } else {
        alert(text);
        return false;
    }
}

function validateAddressForm() {
    var text = "";
    var valid = true;
    if (!$("[id*='form-validate-cart:firstName']").val().match(/^[a-zA-Z -]+[a-zA-Z -]+[a-zA-Z -]+$/)) {
        text += "Le champ 'Prénom' ne peut contenir que des lettres et { ,-}. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart:lastName']").val().match(/^[a-zA-Z -]+[a-zA-Z -]+[a-zA-Z -]+$/)) {
        text += "Le champ 'Nom' ne peut contenir que des lettres et { ,-}. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart:street']").val().match(/^[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+$/)) {
        text += "Le champ 'Rue' ne peut contenir que des lettres, des chiffres, des éspaces et {',-, }. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart:postalCode']").val().match(/^[a-zA-Z0-9]+$/)) {
        text += "Le champ 'Code Postale' ne peut contenir que des chiffres et/ou des lettres.\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart:city']").val().match(/^[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+$/)) {
        text += "Le champ 'Ville' ne peut contenir que des lettres, des chiffres et {',-, }. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart:country']").val().match(/^[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+$/)) {
        text += "Le champ 'Pays' ne peut contenir que des lettres, des chiffres et {',-, }. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (valid) {
        return true;
    } else {
        alert(text);
        return false;
    }
}
function validateModifiedAddress() {
    var text = "";
    var valid = true;
    if (!$("[id*='form-client-address:firstName']").val().match(/^[a-zA-Z -]+[a-zA-Z -]+[a-zA-Z -]+$/)) {
        text += "Le champ 'Prénom' ne peut contenir que des lettres et { ,-}. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-client-address:lastName']").val().match(/^[a-zA-Z -]+[a-zA-Z -]+[a-zA-Z -]+$/)) {
        text += "Le champ 'Nom' ne peut contenir que des lettres et { ,-}. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-client-address:street']").val().match(/^[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+$/)) {
        text += "Le champ 'Rue' ne peut contenir que des lettres, des chiffres, des éspaces et {',-, }. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-client-address:postalCode']").val().match(/^[a-zA-Z0-9]+$/)) {
        text += "Le champ 'Code Postale' ne peut contenir que des chiffres et/ou des lettres.\n";
        valid = false;
    }
    if (!$("[id*='form-client-address:city']").val().match(/^[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+$/)) {
        text += "Le champ 'Ville' ne peut contenir que des lettres, des chiffres et {',-, }. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-client-address:country']").val().match(/^[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+[a-zA-Z0-9 '-]+$/)) {
        text += "Le champ 'Pays' ne peut contenir que des lettres, des chiffres et {',-, }. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (valid) {
        return true;
    } else {
        alert(text);
        return false;
    }
}
$(document).ready(function() {

});
