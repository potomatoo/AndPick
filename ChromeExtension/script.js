document.querySelector("#getBtn").addEventListener("click", function() {
    var nowData = localStorage.getItem("postContent")       
    document.querySelector("#selectText").innerHTML = nowData + "<hr/>"
        
    
    // window.open("http://localhost:8080/accounts/login?chorme=true", "_blank")
})

document.querySelector("#clearBtn").addEventListener("click", function() {
    localStorage.removeItem("postContent")
})

document.querySelector("#saveBtn").addEventListener("click", function() {
    chrome.tabs.executeScript({
        code: "document.getSelection().anchorNode.textContent"
    }, function(selectText) {
        var nowData = localStorage.getItem("postContent")
        if (nowData !== undefined) {
            localStorage.setItem("postContent", nowData + "<hr/>" + selectText[0] + "<br>" + window.location.href)
        } else{
            localStorage.setItem("postContent", selectText[0] + "<br>" + window.location.href)                
        }
    })    
})
