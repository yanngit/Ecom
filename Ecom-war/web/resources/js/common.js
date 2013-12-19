/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function validateConnexion() {
    var text = "";
    var valid = true;
    if (!$("[id*=':login']").val().match(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)) {
        //$("[id*='login']").after('<p>Veuillez saisir une adresse email valide en tant qu\'identifiant</p>');
        text += "Veuillez saisir une adresse email valide en tant qu'identifiant \n";
        valid = false;
    }
    if ($("[id*=':password']").val() == '' || $("[id*='form-connection:password']").val().length < 5) {
        //$("[id*='login']").after('<p>Veuillez saisir une adresse email valide en tant qu\'identifiant</p>');
        text += "Votre mot de passe doit comporter au minimum 6 caractères \n";
        valid = false;
    } else {
        if (!$("[id*=':password']").val().match(/[A-Za-z]+[0-9]*/)) {
            text += "Votre mot de passe doit comporter au minimum un chiffre une lettre et 4 caractères additionnels (chiffre ou lettre)\n";
            valid = false;
        }
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
    if (!$("[id*='form-validate-cart-livraison:firstName']").val().match(/^[a-zA-Z]+[a-zA-Z]+[a-zA-Z]+$/)) {
        text += "Le champ 'Prénom' ne peut contenir que des lettres. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart-livraison:lastName']").val().match(/^[a-zA-Z]+[a-zA-Z]+[a-zA-Z]+$/)) {
        text += "Le champ 'Nom' ne peut contenir que des lettres. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart-livraison:street']").val().match(/^[a-zA-Z\s]*$/)) {
        text += "Le champ 'Rue' ne peut contenir que des lettres et des éspaces. [au moins 3 caractères ]\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart-livraison:postalCode']").val().match(/^[a-zA-Z0-9]+$/)) {
        text += "Le champ 'Code Postale' ne peut contenir que des chiffres et/ou des lettres.\n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart-livraison:city']").val().match(/^[a-zA-Z]+[a-zA-Z]+[a-zA-Z]+$/)) {
        text += "Le champ 'Ville' ne peut contenir que des lettres. [au moins 3 caractères ] \n";
        valid = false;
    }
    if (!$("[id*='form-validate-cart-livraison:country']").val().match(/^[a-zA-Z]+[a-zA-Z]+[a-zA-Z]+$/)) {
        text += "Le champ 'Pays' ne peut contenir que des lettres. [au moins 3 caractères ] \n";
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
