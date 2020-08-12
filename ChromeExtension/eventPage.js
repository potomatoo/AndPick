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
            chrome.storage.sync.get(function(data) {
                if (data.postContent !== undefined) {
                    const nowData = data.postContent
                    chrome.storage.sync.set({
                        postContent: nowData + "<hr/>" + selectText[0] + "<br>" + url[0]
                    })                 
                }
                else{
                    chrome.storage.sync.set({
                        postContent: selectText[0] + "<br>" + url[0]
                    })                
                }
            })       
        })                
    });
})