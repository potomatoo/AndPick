// 크롬 확장의 기능 중에 tabs과 관련된 기능 중에 컨텐츠 페이지를
// 대상으로 코드를 실행해주세요.
chrome.tabs.executeScript({
    code: "document.getSelection().anchorNode.textContent"
}, function(selectText) {
    // 위 코드가 실행된 후에 이 함수를 호출해주세요. 그 떄 result에 담아주세요.
    document.querySelector("#result").innerText = selectText[0]
    
});
