export interface FolderItem {
    title: string;
}

export interface CreateFolderItem {
    CreateFolderName: string;
}

export interface MyArticleList {
    title: string;
}

export interface MypageModule {
    isSidebarActive: boolean;
    IsCreateFolderActive: boolean;
    MyFolderList: FolderItem[];
    CreateFolderItem: CreateFolderItem;
    MyArticleList: MyArticleList[];
}
