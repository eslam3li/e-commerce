var form=document.getElementById("registerForm");
form.onsubmit=function(){
    var password1=document.getElementById("password");
    var password2=document.getElementById("confirmPassword");
    if(password1.value != password2.value) {
        var errorLabel = document.createElement("label");
        errorLabel.innerHTML = "Passwords not matched";
        password2.nextSibling = errorLabel;
        password1.value = "";
        password2.value = "";
    }
}


 var fname=$("#fname");
        var fullname=$("#fullname");
        var email=$("#email");
        var password=$("#password");
        var birthDate=$("#birthDate");
        var Address=$("#Address");
        var job=$("#job");
       
    
function checkpass()
{
   
   
    $('#passtext').html("");

    if (password.val()!== "" ) {
        if (password.val().length < 6) {
           $('#passtext').html("pass length should be more tha 6 character"); 
            
            return false;
        }
        
        re = /[0-9]/;
        if (!re.test(password.val())) {
            $('#passtext').html("password must contain at least one number (0-9)!");
           
            return false;
        }
        re = /[a-z]/;
        if (!re.test(password.val())) {
            $('#passtext').html("password must contain at least one character (a-z)!");
            
            return false;
        }


    } else {
        $('#passtext').html("pass cannot be empty ");
       
        return false;
    }
    
    
    return true;
}
