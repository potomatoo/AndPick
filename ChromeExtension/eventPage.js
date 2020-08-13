var contextMenuItem = {
    "id": "JunhoCatchJob",
    "title": "SaveJunho",
    "contexts": ["selection"]
};
chrome.contextMenus.create(contextMenuItem, () => chrome.runtime.lastError)

chrome.contextMenus.onClicked.addListener(function(clickData) {
    chrome.tabs.executeScript({
        code: "if (localStorage.getItem('postContent') == null) { localStorage.setItem('postContent', document.getSelection().toString() + '<br>')} else { localStorage.setItem('postContent', localStorage.getItem('postContent') + document.getSelection().toString() + '<br>')}"
    })
})        
       


