export interface PostDir {
  postDirId: number;
  userNo: number;
  postDirName: string;
  postList: Post[];
}

export interface Post {
  postId: number;
  userNo: number;
  postDirId: number;
  postTitle: string;
  postContent: string;
  postDate: string;
  postFavorite: boolean;
}

export interface Context {
  showCtx: boolean;
  x: number;
  y: number;
}

export interface MypageModule {
  isSidebarActive: boolean;
  postDirList: PostDir[];
  postDir: Post[];
  post: Post | null;
  postDirId: number | null;
  postId: number | null;
  postDirName: string | null;
  postDirContextMenu: Context;
}
