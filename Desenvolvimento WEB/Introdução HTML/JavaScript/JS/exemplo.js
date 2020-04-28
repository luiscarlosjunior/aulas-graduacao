function validateEmailAddress(email) {
  var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}

function validate() {
  document.write ("alô mundo !!!");
  $("#result").text("");
  var emailaddress = $("#email").val();
  if (validateEmailAddress(emailaddress)) {
    $("#result").text(emailaddress + " é valido:)");
    $("#result").css("color", "green");
  } else {
    $("#result").text(emailaddress + " não está correto, por favor entre novamente:(");
    $("#result").css("color", "red");
  }
  return false;
}
$("#validate").bind("click", validate);
