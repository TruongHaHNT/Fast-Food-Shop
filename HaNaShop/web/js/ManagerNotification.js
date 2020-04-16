/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function showAdNotification(notifyText,notifyID,errorText,errorID){
    if(notifyText !== ""){
        document.getElementById(notifyID).style.height = "20%";
        setTimeout(function(){closeNotification(notifyID);},2000);
    }
    if(errorText !== ""){
        document.getElementById(errorID).style.height = "20%";
        setTimeout(function(){closeNotification(errorID);},2000);
    }
}
function showAdNotification2(text,id){
    alert("ok");
    if(text !== ""){
        document.getElementById(id).style.height = "20%";
        setTimeout(function(){closeNotification(id);},2000);
    }
}

function closeNotification(id){
    document.getElementById(id).style.height = "0%";
}

function popupAlterwindow(id){
        document.getElementById(id).style.height = "100%";
        
    }
function closeAlterwindow(id){
        document.getElementById(id).style.height = "0%";      
}

function loadWindow(text1ID,button1ID,status){
    var text1 = document.getElementById(text1ID);
    var button1 = document.getElementById(button1ID);
    if(status==='Active'){
        text1.innerHTML = "Do you want to delete this Product?";
        button1.value = "Delete this Product";
    }else{
        text1.innerHTML = "Do you want to reactivate this Product?";
        button1.value = "Reactivate this Product";
    }
}

function onResetImg(imgID,fileID,indextext,imgURL){
    document.getElementById(fileID).value="";
    onchangeImage(imgID,fileID,indextext,imgURL);
}

