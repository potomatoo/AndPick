var letters = 'abcdefghijklmnopqrstuvwxyz';
var numbers = '1234567890';
var charset = letters + letters.toUpperCase() + numbers;

function randomElement(array) {
    with (Math)
        return array[floor(random()*array.length)];
}

function randomString(length) {
    var R = '';
    for(var i=0; i<length; i++)
        R += randomElement(charset);
    return R;
}



document.querySelector("#saveBtn").addEventListener("click", function() {
    chrome.tabs.executeScript({
        code: "document.getSelection().toString()"
    }, function(selectText) {
        chrome.tabs.executeScript({
            code: "window.location.href"
        }, function(url) {
            var nowData = localStorage.getItem("postContent")
            if (nowData !== null) {
            localStorage.setItem("postContent", nowData + "<hr/>" + selectText[0] + "<br>" + url[0])
            } else{
            localStorage.setItem("postContent", selectText[0] + "<br>" + url[0])                
            }
        })       
    })   
})


document.querySelector("#getBtn").addEventListener("click", function() {
    var nowData = localStorage.getItem("postContent")       
    document.querySelector("#selectText").innerHTML = nowData + "<hr/>"   
})


document.querySelector("#clearBtn").addEventListener("click", function() {
    localStorage.removeItem("postContent")
})

document.querySelector("#openbrowser").addEventListener("click", function() {           
    var key = randomString(16)
    var scrap = localStorage.getItem("postContent")
    console.log(key)    
    console.log(scrap)
    
    var httpRequest = new XMLHttpRequest()
    var scrapData = {
        id: key,
        scrap: scrap
    }
    httpRequest.open("POST", "http://i3b107.p.ssafy.io:8080/api/public/scrap/save/", true)
    httpRequest.setRequestHeader('Content-Type', 'application/json');        
    httpRequest.send(JSON.stringify(scrapData));        
    window.open(`http://localhost:8080/accounts/login?${key}`, "_blank")
})