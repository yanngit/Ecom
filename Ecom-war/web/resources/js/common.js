/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){ 
    
    $( ".btn-success" ).click(function() {
      var didConfirm = confirm("Voulez vous poursuivre votre achat ?");
      if(didConfirm) {
          return false;
      } else {
          alert('redirection');
          return false;
      }
    }); 
    
}); 

