/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createNewRow(id,checkID,submitsID){
    var checker = parseInt(document.getElementById(checkID).value);
    var table = document.getElementById(id);
    var index = table.rows.length;
    var row = table.insertRow(index-1);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    cell1.innerHTML = index-1;
    cell2.innerHTML = "<input type='text' name='txtCat' value='' required=''>";
    cell3.innerHTML = 0;
    cell4.innerHTML = "New Category";
    checker = checker+1;
    document.getElementById(checkID).value = checker;
    changeSubmit(submitsID,checkID);
}

function deleteRow(id,count,checkID,submitID){
    var checker = parseInt(document.getElementById(checkID).value);
    var table = document.getElementById(id);
    var index1 = parseInt(count);
    var index2 = table.rows.length;
    if(index1<(index2-2)){
        table.deleteRow(index2-2);
        checker = checker-1;
        document.getElementById(checkID).value = checker;
        changeSubmit(submitID,checkID);
    }
}

function checkChangeCatValue(submitbtID,buffercheckerID,defaultValue,CategoryIDvalue,inputID,CategoryParameter,checkerID){
    var value = document.getElementById(inputID).value;
    var bufferchecker = parseInt(document.getElementById(buffercheckerID).value);
    var checker = parseInt(document.getElementById(checkerID).value);
    if(bufferchecker > 0){
        if(defaultValue === value){
            bufferchecker = 0;
            checker = checker-1;
            document.getElementById(CategoryParameter).name = "";
        }
    }else{
        if(defaultValue !== value){
            bufferchecker = 1;
            checker = checker+1;
            document.getElementById(CategoryParameter).name = "txtUpdateCat";
        }else if(defaultValue === value){
            bufferchecker = 0;
            checker = checker-1;
            document.getElementById(CategoryParameter).name = "";
        }
    }
    document.getElementById(CategoryParameter).value = CategoryIDvalue+";;;;;"+value;
    document.getElementById(buffercheckerID).value = bufferchecker;
    document.getElementById(checkerID).value = checker;
    changeSubmit(submitbtID,checkerID);
}

function checkChange(submitID,checkBoxID,checkerID){
    var value = document.getElementById(checkBoxID);
    var checker = parseInt(document.getElementById(checkerID).value);
    if(value.checked){
            checker = checker+1;
    }else{
            checker = checker-1;
    }
    document.getElementById(checkerID).value = checker;
    changeSubmit(submitID,checkerID);
}

function changeSubmit(submitID,checkerID){
    var submits = document.getElementById(submitID);
    var checker = parseInt(document.getElementById(checkerID).value);
    if(checker>0){
        submits.disabled=false;
        submits.style.opacity = 1;
    }else{
        submits.disabled=true;
        submits.style.opacity = 0.8;
    }
}