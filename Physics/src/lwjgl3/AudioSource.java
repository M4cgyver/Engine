package lwjgl3;

import org.lwjgl.openal.AL10;

public class AudioSource {
	private int sourceId;
	private int bufferId;
	
	public AudioSource()
	{
		sourceId = AL10.alGenSources();
		AL10.alSourcef(sourceId, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0,0,0);
	}

	public AudioSource(int buffer)
	{
		this.bufferId = buffer;
		sourceId = AL10.alGenSources();
		AL10.alSourcef(sourceId, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceId, AL10.AL_POSITION, 0,0,0);
	}
	
	public void play(int buffer)
	{
		this.bufferId = buffer;
		AL10.alSourcei(bufferId, AL10.AL_BUFFER, bufferId);
		AL10.alSourcePause(sourceId);
	}

	public void play()
	{
		AL10.alSourcei(bufferId, AL10.AL_BUFFER, bufferId);
		AL10.alSourcePause(sourceId);
	}
	
	protected void finalize()
	{
		AL10.alDeleteSources(sourceId);
	}
}
