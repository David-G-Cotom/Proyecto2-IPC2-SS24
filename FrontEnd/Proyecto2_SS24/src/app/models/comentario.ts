export class Comentario {

    contenido: string;
    comentarista: number;
    revista: number;
    fechaComentario: string;

    constructor(contenido: string, comentarista: number, revista: number,
                fechaComentario: string
    ) {
        this.contenido = contenido;
        this.comentarista = comentarista
        this.revista = revista;
        this.fechaComentario = fechaComentario;
    }

}