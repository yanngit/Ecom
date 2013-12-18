/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function validateConnexion() {
    var text = "";
    var valid = true;
    if (!$("[id*='form-connection:login']").val().match(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/)) {
        //$("[id*='login']").after('<p>Veuillez saisir une adresse email valide en tant qu\'identifiant</p>');
        text += "Veuillez saisir une adresse email valide en tant qu'identifiant \n";
        valid = false;
    }
    if ($("[id*='form-connection:password']").val() === '' || $("[id*='form-connection:password']").val().length < 5) {
        //$("[id*='login']").after('<p>Veuillez saisir une adresse email valide en tant qu\'identifiant</p>');
        text += "Votre mot de passe doit comporter au minimum 6 caractères \n";
        valid = false;
    } else {
        if (!$("[id*='form-connection:password']").val().match(/[A-Za-z]+[0-9]*/)) {
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

$(document).ready(function() {

});
