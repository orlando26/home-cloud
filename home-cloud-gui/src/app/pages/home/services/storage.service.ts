import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { IContent } from '../../../shared/app.interfaces';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private uri = 'http://localhost:8080/cloud/';

  constructor(private http: HttpClient) { }

  requestOptions: Object = {
    responseType: 'text'
  }

  getContent(path: string) {
    return this.http.get<IContent>(this.uri + 'ls?path=' + path);
  }

  uploadFile(fileToUpload: File, path: string): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', fileToUpload);

    const req = new HttpRequest('POST', this.uri + 'upload?folder=' + path, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

  deleteFile(path) {
    return this.http.get<string>(this.uri + 'rmFile?path=' + path, this.requestOptions);
  }

  deleteFolder(path) {
    return this.http.get<string>(this.uri + 'rm?path=' + path, this.requestOptions);
  }

  createFolder(path) {
    return this.http.get<string>(this.uri + 'mkdir?path=' + path, this.requestOptions);
  }

  downloadFile(fileName, folder) {
  }
}
