var contextMenuItem = {
    "id": "saveJunho",
    "title": "SaveJunho",
    "contexts": ["selection"]
};
chrome.contextMenus.create(contextMenuItem)

chrome.contextMenus.onClicked.addListener(function(clickData) {
    chrome.tabs.executeScript({
        code: "document.getSelection().anchorNode.textContent"
    }, function(selectText) {        
        chrome.storage.sync.set({
            postContent: selectText[0]
        })
        window.open("http://i3b107.p.ssafy.io/accounts/login", "_blank")
    });
})