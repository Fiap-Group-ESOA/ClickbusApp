using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ClickbusApi.Models
{
    public class Passagem
    {
        [Key]
        public int Id { get; set; }

        [Required]
        public int UsuarioId { get; set; }

        [ForeignKey("UsuarioId")]
        public Usuario? Usuario { get; set; }

        [Required]
        [StringLength(100)]
        public string Origem { get; set; } = string.Empty;

        [Required]
        [StringLength(100)]
        public string Destino { get; set; } = string.Empty;

        [Required]
        public DateTime DataHora { get; set; }

        [Required]
        public int RodoviariaId { get; set; }

        [ForeignKey("RodoviariaId")]
        public Rodoviaria? Rodoviaria { get; set; }

        [Required]
        [StringLength(50)]
        public string PortaoEmbarque { get; set; } = string.Empty;

        [Required]
        [StringLength(20)]
        public string Status { get; set; } = "Confirmada"; // Confirmada, Cancelada, Finalizada
    }
}
