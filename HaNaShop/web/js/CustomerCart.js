/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function decrementValueCS(AmountID, defaultAmountID, prevAmountID, totalID, UpdateAmountID, ChangeID, txtPrice, itemID, itemname){
    var txtAmount = document.getElementById(AmountID).value;
    var txtDefaultAmount = document.getElementById(defaultAmountID).value;
    var defaultAmount = parseInt(txtDefaultAmount);
    var amount = parseInt(txtAmount);
    var price = parseFloat(txtPrice);
    if(amount>1){
        var total = parseFloat(document.getElementById("totalAllItem").value) - amount*price;
        amount = amount-1;
        checkChangeAmount(amount, defaultAmount, ChangeID);
        document.getElementById(totalID).innerHTML = (amount*price).toFixed(2);
        document.getElementById(UpdateAmountID).value = itemID+";;;;;"+itemname+";;;;;"+amount;
        document.getElementById("totalAllItem").value = (total +amount*price).toFixed(2);
        document.getElementById(prevAmountID).value = amount;
        document.getElementById(AmountID).value = amount;
    }
}
function incrementValueCS(AmountID, defaultAmountID, prevAmountID, totalID, UpdateAmountID, ChangeID, txtPrice, itemID, itemname){
    var txtAmount = document.getElementById(AmountID).value;
    var txtDefaultAmount = document.getElementById(defaultAmountID).value;
    var defaultAmount = parseInt(txtDefaultAmount);
    var amount = parseInt(txtAmount);
    var price = parseFloat(txtPrice);
    var total = parseFloat(document.getElementById("totalAllItem").value) - amount*price;
        amount = amount+1;
        checkChangeAmount(amount, defaultAmount, ChangeID);
        document.getElementById(totalID).innerHTML = (amount*price).toFixed(2);
        document.getElementById(UpdateAmountID).value = itemID+";;;;;"+itemname+";;;;;"+amount;
        document.getElementById("totalAllItem").value = (total +amount*price).toFixed(2);
        document.getElementById(prevAmountID).value = amount;
        document.getElementById(AmountID).value = amount;
}

function changeValueOnInputCS(AmountID, defaultAmountID, prevAmountID, totalID, UpdateAmountID, ChangeID, txtPrice, itemID, itemname){
    var txtPrevAmount = document.getElementById(prevAmountID).value;
    var txtAmount = document.getElementById(AmountID).value;
    var txtDefaultAmount = document.getElementById(defaultAmountID).value;
    var defaultAmount = parseInt(txtDefaultAmount);
    var prevAmount = parseInt(txtPrevAmount);
    var amount = parseInt(txtAmount);
    var price = parseFloat(txtPrice);
    var total = parseFloat(document.getElementById("totalAllItem").value) - prevAmount*price;
    if(amount>0 || txtAmount === ""){
        if(txtAmount === ""){
            amount = 0;
        }
    }else{
        amount = 1;
    }
    document.getElementById(totalID).innerHTML = (amount*price).toFixed(2);
    document.getElementById(UpdateAmountID).value = itemID+";;;;;"+itemname+";;;;;"+amount;
    checkChangeAmount(amount, defaultAmount, ChangeID);
    document.getElementById("totalAllItem").value = (total +amount*price).toFixed(2);
    document.getElementById(prevAmountID).value = amount;
    document.getElementById(AmountID).value = amount;
}

function checkChangeAmount(amount, defaultAmount, ChangeID){
    var change = parseInt(document.getElementById(ChangeID).value);
    var totalcheck = parseInt(document.getElementById("checkChangeAmount").value);
//    check change or not-===============================
    if(change>0){
        if(parseInt(amount) === parseInt(defaultAmount)){
            document.getElementById(ChangeID).value = 0;
            totalcheck = totalcheck-1;
        }
    }else{
        if(parseInt(amount) === parseInt(defaultAmount)){
            document.getElementById(ChangeID).value = 0;
        }
        else{
            document.getElementById(ChangeID).value = 1;
            totalcheck =totalcheck+1;
        }
    }    
//  check total change or not-===================================
    if(totalcheck > 0){
        document.getElementById("confirm").name = "btAction";
        document.getElementById("confirm").value = "Save";
        document.getElementById("confirm").type = "submit";
        document.getElementById("confirm").setAttribute( "onClick", false );
    }else{
        document.getElementById("confirm").name = "";
        document.getElementById("confirm").value = "Purchase";
        document.getElementById("confirm").type = "button";
        document.getElementById("confirm").setAttribute( "onClick", "javascript: popupConfirmwindow('confirmWindow');" );
    }
    document.getElementById("checkChangeAmount").value = totalcheck;
}

function checkDelete(id){
    var check = document.getElementById(id);
    var totalcheck = parseInt(document.getElementById("checkDeleteItems").value);
        if (check.checked) {
            totalcheck = totalcheck+1;
        } else {
            totalcheck = totalcheck -1;
        }
    if(totalcheck > 0){
        document.getElementById("delete").disabled = false;
        document.getElementById("delete").style.opacity = 1;
    }else{
        document.getElementById("delete").disabled = true;
        document.getElementById("delete").style.opacity = 0.5;
    }
    document.getElementById("checkDeleteItems").value = totalcheck;
}

    function popupDeletewindow(id){
        document.getElementById(id).style.height = "100%"; 
    }
    function closeDeletewindow(id){
        document.getElementById(id).style.height = "0%";    
    }
    function popupConfirmwindow(id){
        document.getElementById(id).style.height = "100%";   
    }
    function closeConfirmwindow(id){
        document.getElementById(id).style.height = "0%";   
    }
    
    function CloseItemWindow(id){
        document.getElementById(id).style.height = "0%";
    }
    
    function setItemID(id,valuess){
        document.getElementById(id).value = valuess;
    }