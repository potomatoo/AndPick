var contextMenuItem = {
    "id": "JunhoCatchJob",
    "title": "SaveJunho",
    "contexts": ["selection"]
};
chrome.contextMenus.create(contextMenuItem, () => chrome.runtime.lastError)

chrome.contextMenus.onClicked.addListener(function(clickData) {
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
       


