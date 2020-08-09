var contextMenuItem = {
    "id": "JunhoCatchJob",
    "title": "SaveJunho",
    "contexts": ["selection"]
};
chrome.contextMenus.create(contextMenuItem)

chrome.contextMenus.onClicked.addListener(function(clickData) {
    chrome.tabs.executeScript({
        code: "document.getSelection().anchorNode.textContent"
    }, function(selectText) {                
        chrome.storage.sync.get(function(data) {
            if (data.postContent !== undefined) {
                const nowData = data.postContent
                chrome.storage.sync.set({
                    postContent: nowData + selectText[0]
                })
            }
            else{
                chrome.storage.sync.set({
                    postContent: selectText[0]
                })
            }
        })       
    });
})