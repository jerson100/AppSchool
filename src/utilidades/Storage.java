package utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public abstract class Storage {
	
	public static CloudBlobContainer getContainer(HttpServletRequest request, String name) throws InvalidKeyException, URISyntaxException, StorageException {
		ServletContext contexto= request.getServletContext();
		String url=contexto.getInitParameter("azureStorage");
		CloudStorageAccount storageAccount = CloudStorageAccount.parse(url);
		CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
		CloudBlobContainer container = blobClient.getContainerReference(name);
		return container;
	}
	
	public static void addAndUpdateBlob(HttpServletRequest request, String folderName, String fileName, String contentType, String containerName, InputStream data, long longitud) throws InvalidKeyException, URISyntaxException, StorageException, IOException {
		CloudBlobContainer container = getContainer(request, containerName);
		CloudBlockBlob blob = container.getBlockBlobReference(folderName+"/"+fileName);
		blob.getProperties().setContentType(contentType);
		blob.upload(data,longitud);
	}
	
	public static void deleteBlob(HttpServletRequest request, String folderName, String fileName, String containerName) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudBlobContainer container = getContainer(request, containerName);
		CloudBlockBlob blob = container.getBlockBlobReference(folderName+"/"+fileName);
		blob.delete();
	}
	
	public static void deleteBlob(HttpServletRequest request, String path, String containerName) throws InvalidKeyException, URISyntaxException, StorageException {
		CloudBlobContainer container = getContainer(request, containerName);
		CloudBlockBlob blob = container.getBlockBlobReference(path);
		blob.delete();
	}

	public static void download(HttpServletResponse response, HttpServletRequest request, String path, String containerName, String title) throws InvalidKeyException, URISyntaxException, StorageException, IOException {
		CloudBlobContainer container = getContainer(request, containerName);
		CloudBlockBlob blob = container.getBlockBlobReference(path);
		blob.downloadAttributes();
		response.setHeader("Content-Disposition", "attachment; filename="+title);
		System.out.println("Data: "+blob.getProperties().getContentType());
		System.out.println("Data: "+blob.getProperties().getContentDisposition());
		System.out.println("Data: "+blob.getProperties().getLength());
		response.setContentType(blob.getProperties().getContentType());
		try(OutputStream out = response.getOutputStream()){
			blob.download(out);			
		}
	}

	public static void download(HttpServletResponse response, HttpServletRequest request, String path,
			String containerName) throws StorageException, URISyntaxException, InvalidKeyException, IOException {
		CloudBlobContainer container = getContainer(request, containerName);
		CloudBlockBlob blob = container.getBlockBlobReference(path);
		blob.downloadAttributes();
		int index = path.lastIndexOf("/");
		response.setHeader("Content-Disposition", "attachment; filename="+path.substring(index+1));
		System.out.println("Data: "+blob.getProperties().getContentType());
		System.out.println("Data: "+blob.getProperties().getContentDisposition());
		System.out.println("Data: "+blob.getProperties().getLength());
		response.setContentType(blob.getProperties().getContentType());
		try(OutputStream out = response.getOutputStream()){
			blob.download(out);			
		}
	}
	
}
