/**
 * Created by Azat on 21.03.2017.
 */
function ajaxResultGroups() {
     var nb_groups = document.getElementById("nb_groups");
     var xmlhttp = new XMLHttpRequest();
     var x1 = document.getElementById("para").nodeValue;
     var x2 = document.getElementById("data").nodeValue;
        alert("nbInData?para="+x1+"&data="+x2);
     xmlhttp.open("GET", "nbInData?para="+x1, true);
     xmlhttp.send();
     xmlhttp.onreadystatechange=function() {
         if (xmlhttp.readyState < 4) {
            nb_groups.innerHTML = "<font color='gray' size='2'>Yuqlanmoqda...</font>";
         }
         if (xmlhttp.readyState==4 && xmlhttp.status==200) {
             var response = xmlhttp.responseText;
            nb_groups.innerHTML = response;
         }
     }
}