export interface SidebarList {
    title: string;
}

export interface ArticleItem {
    title: string;
    content: string;
}

export interface ArticleList {
    articleItems: ArticleItem[];
}

export interface MypageModule {
    isSidebarActive: boolean;
    FolderList: SidebarList[];
    MyArticleList: ArticleList[];
}
