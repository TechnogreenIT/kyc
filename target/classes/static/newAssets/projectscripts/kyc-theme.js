var myTheme = "";

$(document).ready(function() {

  $('body').on('change', '.theme-switch input:radio', function() {
    var theme = $(this).val();
    localStorage.setItem('kyc-theme', theme);
    $('body').attr('data-ma-theme', theme);
  });

  var myTheme = localStorage.getItem('kyc-theme');
  if (myTheme == "" || myTheme == null || myTheme == undefined) {
    myTheme = "blue";
  }
  setKYCTheme(myTheme);
  themeSwitchActivate(myTheme);
});

function setKYCTheme(myTheme) {
  $('body').attr('data-ma-theme', myTheme);
}

function themeSwitchActivate(myTheme){
  var currentTheme = "div.theme-switch > .btn-group--colors > .bg-"+myTheme;
  $(currentTheme).addClass('active');
}