endpoints para carrera:

    GET -> localHost:8080/careers                                   (trae todas las carreras)

    GET -> localHost:8080/careers/OrderByCantStudentRegistered      (trae todas las carreras ordenadas por canntidad de inscriptos)

    GET -> localHost:8080/careers/getReport                         (trae un reporte de las carreras ordenado, con los inscriptos y egresados de cada anio ordenados de manera cronologica)

    POST -> localHost:8080/careers/"idCarrera"/"idEstudiante"       (matricula un estudiante en la carrera)

    POST -> localHost:8080/careers                                  (agrega una carrera)

    PUT -> localHost:8080/careers/id                                (update a carrera)

    DELETE -> localHost:8080/careers/id                             (borra una carrera)


endpoints para estudiante:

    GET -> localHost:8080/students                   (trae todos los estudiantes ordenados por apellido)

    GET -> localHost:8080/students/byGenre/"genero"  (trae todos los estudiantes de un genero)

    GET -> localHost:8080/students/"carrera"/ciudad  (trae los estudiantes que cursan la carrera y son de esa ciudad)

    GET -> localHost:8080/students/nroLibreta        (trae un estudiante por su libreta)

    POST -> localHost:8080/students                  (inserta un estudiante)

    PUT -> localHost:8080/students/dni               (actualiza un alumno)

    DELETE -> localHost:8080/students/dni            (elimina un alumno)
