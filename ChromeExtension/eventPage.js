var contextMenuItem = {
    "id": "JunhoCatchJob",
    "title": "SaveJunho",
    "contexts": ["selection"]
};
chrome.contextMenus.create(contextMenuItem, () => chrome.runtime.lastError)

chrome.contextMenus.onClicked.addListener(function(clickData) {
    chrome.tabs.executeScript({
        code: "document.getSelection().anchorNode.textContent"
    }, function(selectText) {
        chrome.tabs.executeScript({
            code: "window.location.href"
        }, function(url) {
            var nowData = localStorage.getItem("postContent")
            if (nowData !== undefined) {
            localStorage.setItem("postContent", nowData + "<hr/>" + selectText[0] + "<br>" + window.location.href)
            } else{
            localStorage.setItem("postContent", selectText[0] + "<br>" + window.location.href)                
            }
        })       
    })   
})