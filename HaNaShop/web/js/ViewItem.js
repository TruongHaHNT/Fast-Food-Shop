/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function decrementValue(txtQuantity, txtPrice) {
    var quan = parseInt(txtQuantity,10);
    var prices = parseFloat(txtPrice);
    if(quan>1){
        
        quan = quan-1;
        document.getElementById("amount").value = quan;
        document.getElementById("name").innerHTML = quan;
        var total = quan*prices;
        document.getElementById("price").innerHTML = total.toFixed(2);
        document.getElementById("total").innerHTML = total.toFixed(2);
        
    }
}

function incrementValue(txtQuantity, txtPrice) {
    var quan = parseInt(txtQuantity,10);
    var prices = parseFloat(txtPrice);
        quan = quan+1;
        document.getElementById("amount").value = quan;
        document.getElementById("name").innerHTML = quan;
        var total = quan*prices;
        document.getElementById("price").innerHTML = total.toFixed(2);
        document.getElementById("total").innerHTML = total.toFixed(2);
}

function changeValueOnInput(txtQuantity, txtPrice){
    var quan = parseInt(txtQuantity,10);
    var prices = parseFloat(txtPrice);
    if(quan>0 || txtQuantity===""){
        if(txtQuantity === ""){
            quan = 0;
        }
        document.getElementById("name").innerHTML = quan;
        var total = quan*prices;
        document.getElementById("price").innerHTML = total.toFixed(2);
        document.getElementById("total").innerHTML = total.toFixed(2);
    }
    else{
        document.getElementById("amount").value = 1;
        document.getElementById("name").innerHTML = 1;
        document.getElementById("price").innerHTML = total.toFixed(1);
        document.getElementById("total").innerHTML = total.toFixed(1);
    }
}
function changeItemID(id, value){
    document.getElementById(id).value = value;
}