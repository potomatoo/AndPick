export interface PostDir {
    postDirID: number;
    userNo: number;
    postDirName: string;
    postList: Post[]
}

export interface Post {
    postId: number;
    userNo: number;
    postDirID: number;
    postTitle: string
    postContent: string;
    postDate: string;
    postFavorite: boolean;
}

export interface MypageModule {
    isSidebarActive: boolean;
    postDirList: PostDir[];
}

