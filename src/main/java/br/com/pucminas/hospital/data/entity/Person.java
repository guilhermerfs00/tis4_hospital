//package br.com.pucminas.hospital.data.entity;
//
//import org.springframework.data.annotation.Id;
//
//import java.io.Serializable;
//
//
//@Entity
//@Table(name="person")
//public class Person implements Serializable{
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", nullable = false)
//	private Long id;
//
//	@Column(name = "first_name", nullable = false, length = 80)
//	private String firstName;
//
//	@Column(name = "last_name", nullable = false, length = 80)
//	private String lastName;
//
//	@Column(nullable = false, length = 100)
//	private String address;
//
//	@Column(nullable = false, length = 6)
//	private String gender;
//
//	@Column(nullable = false)
//	private Boolean enabled;
//
//	public Long getAsdsad() {
//		return asdsad;
//	}
//
//	public void setAsdsad(Long asdsad) {
//		this.asdsad = asdsad;
//	}
//
//}