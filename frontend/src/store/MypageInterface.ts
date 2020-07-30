export interface FolderItem {
    title: string;
    articleList: ArticleItem[]
}

export interface ArticleItem {
    title: string;
    content: string;
}

export interface MypageModule {
    isSidebarActive: boolean;
    folderList: FolderItem[];
    myArticleList: ArticleItem[];
}

