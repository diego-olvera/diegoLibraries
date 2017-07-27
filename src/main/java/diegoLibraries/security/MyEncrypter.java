package diegoLibraries.security;

import diegoLibraries.typeConverter.FullConverter;

public class MyEncrypter extends Encrypter{
	
	@Override
	public byte[] encrypt(byte[] bytes) {
		int i=0,j=bytes.length+20,l;
		byte[] bytesEncrypted=new byte[j];
		for(byte b:bytes){
			bytesEncrypted[i++]=((byte)(b^(j-20)));
		}
		for(l=0;i<j;i++){
			bytesEncrypted[i]=((byte)(bytesEncrypted[l++]^(l*i)));
		}
		return bytesEncrypted;
	}

	@Override
	public byte[] decrypt(byte[] bytes) {
		int l=bytes.length,i=0,j=l>20?l-20:l;
		byte[] bytesDecrypted=new byte[j];
		for(i=0;i<j;i++){
			bytesDecrypted[i]=((byte)(bytes[i]^j));
		}
		return bytesDecrypted;
	}
	public static void main(String[] args) {
		MyEncrypter encriptador=new MyEncrypter();
		String cadenaPrueba;
		cadenaPrueba="pedroGasol43510zp9010RSZ";
		cadenaPrueba="pedroGasol43510zp9010RSZP";
		byte[] bytesEncriptados=encriptador.encrypt(cadenaPrueba.getBytes());
		byte[] bytesDesencriptados;
		float f=4.89f,f2;
		int i1=86,i2;
		long l1=67,l2;
		double d1=58.90,d2;
		System.out.println("Cadena original:"+cadenaPrueba);
		System.out.println("Cadena encriptada:"+new String(bytesEncriptados));
		bytesDesencriptados=encriptador.decrypt(bytesEncriptados);
		System.out.println("Cadena desencriptada:"+new String(bytesDesencriptados));
		System.out.println("---------------------");
		System.out.println("Flotante original:"+f);
		bytesEncriptados=encriptador.encrypt(f);
		System.out.println("Bytes flotante="+bytesEncriptados);
		bytesDesencriptados=encriptador.decrypt(bytesEncriptados);
		f2=FullConverter.toFloat(bytesDesencriptados);
		System.out.println("Flotante="+f2);
		System.out.println("---------------------");
		System.out.println("Entero original:"+i1);
		bytesEncriptados=encriptador.encrypt(i1);
		System.out.println("Bytes entero="+bytesEncriptados);
		bytesDesencriptados=encriptador.decrypt(bytesEncriptados);
		i2=FullConverter.toInt(bytesDesencriptados);
		System.out.println("Entero="+i2);
		System.out.println("---------------------");
		System.out.println("Entero largo original:"+l1);
		bytesEncriptados=encriptador.encrypt(l1);
		System.out.println("Bytes entero largo="+bytesEncriptados);
		bytesDesencriptados=encriptador.decrypt(bytesEncriptados);
		l2=FullConverter.toLong(bytesDesencriptados);
		System.out.println("Entero largo="+l2);
		System.out.println("---------------------");
		System.out.println("Doble original:"+d1);
		bytesEncriptados=encriptador.encrypt(d1);
		System.out.println("Bytes double="+bytesEncriptados);
		bytesDesencriptados=encriptador.decrypt(bytesEncriptados);
		d2=FullConverter.toDouble(bytesDesencriptados);
		System.out.println("double="+d2);
	
		System.out.println("---------------------");
		System.out.println("Ahora directo con double");
		System.out.println("Doble original:"+i1);
		//d1=90;
		double otro=encriptador.simpleEncrypt(i1);
		/*System.out.println("otro:"+otro);
		bytesEncriptados=Converter.toBytes(otro);
		System.out.println("bytes="+bytesEncriptados);
		bytesEncriptados=encriptador.dencrypt(bytesEncriptados);
		System.out.println("otro de nuevo="+Converter.toDouble(bytesEncriptados));*/
		double elMismo=encriptador.simpleDesencrypt(otro);
		System.out.println("El mismo:"+elMismo);
		System.out.println("---------------------");
		System.out.println("Ahora directo con entero");
		//d1=90;
		System.out.println("Original:"+i1);
		double otroEntero=encriptador.simpleEncrypt(i1);
		/*System.out.println("otro:"+otro);
		bytesEncriptados=Converter.toBytes(otro);
		System.out.println("bytes="+bytesEncriptados);
		bytesEncriptados=encriptador.dencrypt(bytesEncriptados);
		System.out.println("otro de nuevo="+Converter.toDouble(bytesEncriptados));*/
		int elMismoEntero=(int) encriptador.simpleDesencrypt((double)otroEntero);
		System.out.println("El mismo:"+elMismoEntero);

	}

}
