/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function showNotification(text,id){
    if(text !== ""){
        document.getElementById(id).style.height = "20%";
        setTimeout(function(){closeNotification(id);},2000);
    }
}
function closeNotification(id){
    document.getElementById(id).style.height = "0%";
}

function changeValue(value, id){
    document.getElementById(id).value = value;
}

function loadWarningWindow(text){
    var message = document.getElementById("alterText");
    var confirmBT = document.getElementById("confirm");
    if(text === ""){
        message.innerHTML = "Do you want to reactivate selected items";
        confirmBT.value = "Confirm reactivate";
    }else{
        message.innerHTML = "Do you want to delete selected items";
        confirmBT.value = "Confirm delete";
    }
}

function checkAlter(id){
    var checkBox = document.getElementById(id);
    var totalcheck = parseInt(document.getElementById("countAlterRow").value);
        if (checkBox.checked) {
            totalcheck = totalcheck+1;
        } else {
            totalcheck = totalcheck-1;
        }
    if(totalcheck > 0){
        document.getElementById("btAlterBox").disabled = false;
        document.getElementById("btAlterBox").style.opacity = 1;
    }else{
        document.getElementById("btAlterBox").disabled = true;
        document.getElementById("btAlterBox").style.opacity = 0.5;
    }
    document.getElementById("countAlterRow").value = totalcheck;
}

function popupAlterwindow(id){
        document.getElementById(id).style.height = "100%"; 
    }
function closeAlterwindow(id){
        document.getElementById(id).style.height = "0%";      
}

function setItemID(id,valuess){
        document.getElementById(id).value = valuess;
    }