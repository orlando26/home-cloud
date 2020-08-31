export interface IDirectory {
  name: string;
  lastModified: string;
}

export interface IFile {
  name: string;
  size: string;
  extension: string;
  lastModified: string;
}

export interface IContent {
  path: string;
  directories: IDirectory[];
  files: IFile[];
}
