import { Component, OnInit } from '@angular/core';
import { IContent } from 'src/app/shared/app.interfaces';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss']
})
export class ContentComponent implements OnInit {

  public currentPath: string = '/';

  newFolderName = '';

  public content: IContent = {
    path: '',
    directories: [],
    files: []
  }

  fileToUpload: File = null;

  constructor(private storageApi: StorageService) { }

  ngOnInit(): void {
    this.storageApi.getContent(this.currentPath).subscribe(
      res => {
        this.content = res;
        console.log(this.content);
      }
    );
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  getFileName() {
    if (this.fileToUpload) {
      return this.fileToUpload.name;
    } else {
      return 'Add file';
    }
  }

  uploadFile() {
    this.storageApi.uploadFile(this.fileToUpload, this.content.path).subscribe(
      res => {
        this.ngOnInit();
        console.log(res);
      }
    );
  }

  deleteFile(fileName) {
    this.storageApi.deleteFile(this.content.path + fileName).subscribe(
      data => {
        this.ngOnInit();
        console.log(data);
      }
    );
  }

  deleteFolder(folderName) {
    this.storageApi.deleteFolder(this.content.path + folderName).subscribe(
      data => {
        this.ngOnInit();
        console.log(data);
      }
    );
  }

  createFolder() {
    this.storageApi.createFolder(this.content.path + this.newFolderName).subscribe(
      data => {
        this.ngOnInit();
        console.log(data);
      }
    );
  }

  changeDir(folderName) {
    this.currentPath += folderName;
    this.ngOnInit();
  }

  goBack() {
    if (this.currentPath != '/') {
      let folder = this.currentPath.split('/')[this.currentPath.split('/').length - 2] + '/';
      this.currentPath = this.currentPath.replace(folder, '');
      this.ngOnInit();
    }
  }

  getImageByExt(extension) {
    let img = '';
    switch (extension) {
      case 'txt':
        img = '/assets/img/document.png';
        break;
      case 'png':
      case 'jpg':
      case 'JPG':
        img = '/assets/img/image-file.png';
        break;
      case 'docx':
        img = '/assets/img/word.png';
        break;
      case 'ppt':
      case 'pptx':
        img = '/assets/img/powerpoint.png';
        break;
      case 'pdf':
      case 'PDF':
        img = '/assets/img/pdf.png';
        break;
      case 'xlsx':
        img = '/assets/img/excel.png';
        break;
      case 'csv':
        img = '/assets/img/csv.png';
        break;
      case 'pdf':
        img = '/assets/img/pdf.png';
        break;
      case 'rar':
        img = '/assets/img/rar.png';
        break;
      case 'zip':
      case '7z':
        img = '/assets/img/zip.png';
        break;
      case 'mp4':
        img = '/assets/img/film.png';
        break;
      case 'wav':
      case 'mp3':
        img = '/assets/img/audio-file.png';
        break;
      default:
        img = '/assets/img/blank-file.png';
        break;
    }
    return img;
  }

}
