package i.valeriytimakov.usersegmentation.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class MultipartResourceWrapper implements Resource {
	
	private MultipartFile file;

	public MultipartResourceWrapper(MultipartFile file) {
		this.file = file;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return file.getInputStream();
	}

	@Override
	public boolean exists() {
		return file != null;
	}

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public boolean isOpen() {
		return true;
	}

	@Override
	public URL getURL() throws IOException {
		return null;
	}

	@Override
	public URI getURI() throws IOException {
		return null;
	}

	@Override
	public File getFile() throws IOException {
		return null;
	}

	@Override
	public long contentLength() throws IOException {
		return file.getSize();
	}

	@Override
	public long lastModified() throws IOException {
		return System.currentTimeMillis();
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {
		return null;
	}

	@Override
	public String getFilename() {
		return file.getOriginalFilename();
	}

	@Override
	public String getDescription() {
		return null;
	}

}
